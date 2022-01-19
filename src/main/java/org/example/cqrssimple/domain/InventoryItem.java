package org.example.cqrssimple.domain;

import lombok.Getter;
import org.example.cqrssimple.event.ItemDeactivatedEvent;
import org.example.cqrssimple.event.ItemRenamedEvent;
import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemCheckedInEvent;
import org.example.cqrssimple.event.ItemCreatedEvent;
import org.example.cqrssimple.event.ItemRemovedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
public class InventoryItem {
    private final UUID uuid;
    private String name;
    private int quantity = 0;
    private final List<Event> uncommittedChanges = new ArrayList<>();
    private boolean activated = true;

    public InventoryItem(UUID uuid) {
        this.uuid = uuid;
    }

    public InventoryItem(UUID uuid, String name) {
        this(uuid);
        this.name = name;
        uncommittedChanges.add(new ItemCreatedEvent(uuid, name));
    }

    public void checkIn(int quantity) {
        this.uncommittedChanges.add(new ItemCheckedInEvent(this.uuid, quantity));
    }

    public void remove(int quantity) {
        if (quantity > this.quantity) {
            throw new NotEnoughItemsException(uuid, this.quantity, quantity);
        }
        this.uncommittedChanges.add(new ItemRemovedEvent(this.uuid, quantity));
    }

    public void rename(String newName) {
        this.uncommittedChanges.add(new ItemRenamedEvent(this.uuid, newName));
        name = newName;
    }

    public void deactivate() {
        activated = false;
        uncommittedChanges.add(new ItemDeactivatedEvent(uuid));
    }

    public void apply(ItemCreatedEvent itemCreatedEvent) {
        this.name = itemCreatedEvent.getItemName();
    }

    public void apply(ItemCheckedInEvent itemCheckedInEvent) {
        this.quantity += itemCheckedInEvent.getQuantity();
    }

    public void apply(ItemRemovedEvent itemRemovedEvent) {
        this.quantity -= itemRemovedEvent.getQuantity();
    }

    public void apply(ItemRenamedEvent itemRenamedEvent) {
        name = itemRenamedEvent.getNewName();
    }

    public void apply(ItemDeactivatedEvent itemDeactivatedEvent) {
        activated = false;
    }
}
