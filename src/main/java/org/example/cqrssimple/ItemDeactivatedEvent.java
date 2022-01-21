package org.example.cqrssimple;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ItemDeactivatedEvent extends IDomainEvent {
    public ItemDeactivatedEvent(String itemId) {
        super(itemId);
    }
}
