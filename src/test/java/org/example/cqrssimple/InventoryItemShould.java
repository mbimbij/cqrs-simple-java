package org.example.cqrssimple;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryItemShould {
    @Test
    void produceItemCreatedEvent_whenCreated() {
        // GIVEN
        String itemId = UUID.randomUUID().toString();
        String itemName = "item name";

        List<ItemEvent> events = new ArrayList<>();

        // WHEN
        InventoryItem item = new InventoryItem(itemId, itemName, events);

        // THEN
        assertThat(events).contains(new ItemCreatedEvent(itemId, itemName));
    }
}
