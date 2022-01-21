package org.example.cqrssimple.domain;

public class ItemDeactivatedEvent extends IDomainEvent {
    public ItemDeactivatedEvent(String itemId) {
        super(itemId);
    }
}
