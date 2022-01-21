package org.example.cqrssimple;

import java.util.List;

public class InventoryItem {
    private final String id;
    private final String name;

    public InventoryItem(String id, String name, List<ItemEvent> events) {
        this.id = id;
        this.name = name;
        events.add(new ItemCreatedEvent(id,name));
    }
}
