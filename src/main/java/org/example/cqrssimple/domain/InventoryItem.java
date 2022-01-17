package org.example.cqrssimple.domain;

import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemCreatedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryItem {
    private final UUID uuid;
    private String name;
    private List<Event> uncommittedChanges = new ArrayList<>();

    public InventoryItem(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        uncommittedChanges.add(new ItemCreatedEvent(uuid, name));
    }

    public List<Event> getUncommittedChanges() {
        return uncommittedChanges;
    }

    public void setUncommittedChanges(List<Event> uncommittedChanges) {
        this.uncommittedChanges = uncommittedChanges;
    }
}
