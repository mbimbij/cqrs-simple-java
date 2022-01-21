package org.example.cqrssimple;

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

    @BeforeEach
    void setUp() {
        itemId = UUID.randomUUID().toString();
        events = new ArrayList<>();
    }

    @Test
    void produceItemCreatedEvent_whenCreated() {
        // WHEN
        InventoryItem item = new InventoryItem(itemId, itemName, events);

        // THEN
        assertThat(events).contains(new ItemCreatedEvent(itemId, itemName));
    }

    @Test
    void produceItemCreatedEvent_whenCreated_viaFactoryMethod() {
        // WHEN
        InventoryItem item = InventoryItem.addToCatalog(itemId, itemName, events);

        // THEN
        assertThat(events).contains(new ItemCreatedEvent(itemId, itemName));
    }
}
