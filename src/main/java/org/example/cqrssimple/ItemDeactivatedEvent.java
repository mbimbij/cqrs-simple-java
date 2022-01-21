package org.example.cqrssimple;

public class ItemDeactivatedEvent extends IDomainEvent {
    public ItemDeactivatedEvent(String itemId) {
        super(itemId);
    }
}
