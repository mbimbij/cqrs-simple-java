package org.example.cqrssimple.domain.aggregate;

import org.example.cqrssimple.domain.IDomainEvent;
import org.example.cqrssimple.domain.ItemCheckedInEvent;
import org.example.cqrssimple.domain.ItemCreatedEvent;
import org.example.cqrssimple.domain.ItemDeactivatedEvent;
import org.example.cqrssimple.domain.ItemRemovedEvent;
import org.example.cqrssimple.domain.ItemRenamedEvent;

import java.util.List;
import java.util.Objects;

public class InventoryItem {
    private String id;
    private String name;
    private boolean deactivated = false;

    /*
    Alternative #1 -> création via constructeur
     */
    public InventoryItem(String id, String name, List<IDomainEvent> events) {
        ItemCreatedEvent event = new ItemCreatedEvent(id, name);
        apply(event);
        events.add(event);
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

    public void deactivate(List<IDomainEvent> events) {
        if (deactivated) {
            return;
        }

        ItemDeactivatedEvent event = new ItemDeactivatedEvent(id);
        apply(event);
        events.add(event);
    }

    public void remove(int quantity, List<IDomainEvent> events) {
        events.add(new ItemRemovedEvent(id, quantity));
    }

    public void rename(String newName, List<IDomainEvent> events) {
        if (Objects.equals(name, newName)) {
            return;
        }

        ItemRenamedEvent event = new ItemRenamedEvent(id, newName);
        apply(event);
        events.add(event);
    }

    void apply(ItemCreatedEvent itemCreatedEvent) {
        this.id = itemCreatedEvent.getItemId();
        this.name = itemCreatedEvent.getItemName();
    }

    void apply(ItemDeactivatedEvent event) {
        deactivated = true;
    }

    void apply(ItemRenamedEvent event) {
        name = event.getNewName();
    }
}
