package org.example.cqrssimple.domain.event;

public class ItemDeactivatedEvent extends IDomainEvent {
    public ItemDeactivatedEvent(String itemId) {
        super(itemId);
    }
}
