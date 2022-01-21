package org.example.cqrssimple;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryItemShould {

    private final String itemName = "item name";
    private String itemId;
    private List<ItemEvent> events;
    private InventoryItem item;

    @BeforeEach
    void setUp() {
        itemId = UUID.randomUUID().toString();
        events = new ArrayList<>();
        item = new InventoryItem(itemId, itemName, events);
    }

    @Test
    void produceItemCreatedEvent_whenCreated() {
        // GIVEN item created in setup

        // THEN
        ItemCreatedEvent expectedEvent = new ItemCreatedEvent(itemId, itemName);
        assertThat(events).contains(expectedEvent);
    }

    @Test
    void produceItemCreatedEvent_whenCreated_viaFactoryMethod() {
        // WHEN
        ArrayList<ItemEvent> events = new ArrayList<>();
        InventoryItem.addToCatalog(itemId, itemName, events);

        // THEN
        ItemCreatedEvent expectedEvent = new ItemCreatedEvent(itemId, itemName);
        assertThat(events).contains(expectedEvent);
    }

    @Test
    void produceItemCheckedInEvent_whenCheckIn() {
        // GIVEN
        int checkInQuantity = 2;

        // WHEN
        item.checkIn(checkInQuantity, events);

        // THEN
        ItemCheckedInEvent expectedEvent = new ItemCheckedInEvent(itemId, checkInQuantity);
        assertThat(events).contains(expectedEvent);
    }

    @Test
    void produceItemDeletedEvent_whenDelete() {
        // WHEN
        item.delete(events);

        // THEN
        ItemDeletedEvent expectedEvent = new ItemDeletedEvent(itemId);
        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(events).filteredOn(event -> event instanceof ItemDeletedEvent).hasSize(1);
            softAssertions.assertThat(events).contains(expectedEvent);
        });
    }

    @Test
    void notProduceEvent_whenDelete_andItemAlreadyDeleted() {
        // WHEN
        item.delete(events);
        item.delete(events);

        // THEN
        ItemDeletedEvent expectedEvent = new ItemDeletedEvent(itemId);
        assertThat(events).filteredOn(event -> event instanceof ItemDeletedEvent).hasSize(1);
    }

    @Test
    void produceItemRemovedEvent_whenRemove() {
        // WHEN
        int quantity = 2;
        item.remove(quantity, events);

        // THEN
        ItemRemovedEvent expectedEvent = new ItemRemovedEvent(itemId, quantity);
        assertThat(events).contains(expectedEvent);
    }
}
