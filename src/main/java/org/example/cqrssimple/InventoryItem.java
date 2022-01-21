package org.example.cqrssimple;

import java.util.List;

public class InventoryItem {
    private final String id;
    private final String name;

    /*
    Alternative #1 -> création via constructeur
     */
    public InventoryItem(String id, String name, List<ItemEvent> events) {
        this.id = id;
        this.name = name;
        events.add(new ItemCreatedEvent(id, name));
    }

    /*
    Alternative #2 -> création via factory method (+ refacto constructeur en privé)
     */
    public static InventoryItem addToCatalog(String id, String name, List<ItemEvent> events) {
        return new InventoryItem(id, name, events);
    }
}
