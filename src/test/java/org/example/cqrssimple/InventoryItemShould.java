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
    private List<IDomainEvent> events;
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
        ArrayList<IDomainEvent> events = new ArrayList<>();
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
    void produceItemDeactivatedEvent_whenDeactivate() {
        // WHEN
        item.deactivate(events);

        // THEN
        ItemDeactivatedEvent expectedEvent = new ItemDeactivatedEvent(itemId);
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(events).filteredOn(event -> event instanceof ItemDeactivatedEvent).hasSize(1);
            softAssertions.assertThat(events).contains(expectedEvent);
        });
    }

    @Test
    void notProduceEvent_whenDeactivate_andItemAlreadyDeactivated() {
        // WHEN
        item.deactivate(events);
        item.deactivate(events);

        // THEN
        assertThat(events).filteredOn(event -> event instanceof ItemDeactivatedEvent).hasSize(1);
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

    @Test
    void produceItemRenamedEvent_whenRenameItem() {
        // WHEN
        String newName = "new name";
        item.rename(newName, events);

        // THEN
        ItemRenamedEvent expectedEvent = new ItemRenamedEvent(itemId, newName);
        assertThat(events).contains(expectedEvent);
    }

    @Test
    void notProduceEvent_whenRenameItem_butNameHasntChanged() {
        // WHEN
        item.rename(itemName, events);

        // THEN
        assertThat(events).filteredOn(event -> event instanceof ItemRenamedEvent).isEmpty();
    }
}
