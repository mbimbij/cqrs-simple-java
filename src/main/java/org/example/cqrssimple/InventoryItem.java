package org.example.cqrssimple;

import java.util.List;

public class InventoryItem {
    private final String id;
    private final String name;
    private boolean deleted = false;

    /*
    Alternative #1 -> création via constructeur
     */
    public InventoryItem(String id, String name, List<IDomainEvent> events) {
        this.id = id;
        this.name = name;
        events.add(new ItemCreatedEvent(id, name));
    }

    /*
    Alternative #2 -> création via factory method (+ refacto constructeur en privé)
     */
    public static InventoryItem addToCatalog(String id, String name, List<IDomainEvent> events) {
        return new InventoryItem(id, name, events);
    }

    public void checkIn(int checkInQuantity, List<IDomainEvent> events) {
        events.add(new ItemCheckedInEvent(id, checkInQuantity));
    }

    public void delete(List<IDomainEvent> events) {
        if (deleted) {
            return;
        }

        deleted = true;
        events.add(new ItemDeletedEvent(id));
    }

    public void remove(int quantity, List<IDomainEvent> events) {
        events.add(new ItemRemovedEvent(id, quantity));
    }

    public void rename(String newName, List<IDomainEvent> events) {
        events.add(new ItemRenamedEvent(id, newName));
    }

    public void deactivate(List<IDomainEvent> events) {
        events.add(new ItemDeactivatedEvent(id));
    }
}
