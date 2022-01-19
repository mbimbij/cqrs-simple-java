package org.example.cqrssimple.domain;

import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemCheckedInEvent;
import org.example.cqrssimple.event.ItemCreatedEvent;
import org.example.cqrssimple.event.ItemRemovedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryItem {
    private final UUID uuid;
    private String name;
    private int quantity = 0;
    private List<Event> uncommittedChanges = new ArrayList<>();

    public InventoryItem(UUID uuid) {
        this.uuid = uuid;
    }

    public InventoryItem(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        uncommittedChanges.add(new ItemCreatedEvent(uuid, name));
    }

    public List<Event> getUncommittedChanges() {
        return uncommittedChanges;
    }

    public void apply(ItemCreatedEvent itemCreatedEvent) {
        this.name = itemCreatedEvent.getItemName();
    }

    public void checkIn(int quantity) {
        this.uncommittedChanges.add(new ItemCheckedInEvent(this.uuid, quantity));
    }

    public void remove(int quantity) {
        this.uncommittedChanges.add(new ItemRemovedEvent(this.uuid, quantity));
    }
}
