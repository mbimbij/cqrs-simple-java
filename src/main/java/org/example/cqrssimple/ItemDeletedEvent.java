package org.example.cqrssimple;

public class ItemDeletedEvent extends IDomainEvent {
    public ItemDeletedEvent(String itemId) {
        super(itemId);
    }
}
