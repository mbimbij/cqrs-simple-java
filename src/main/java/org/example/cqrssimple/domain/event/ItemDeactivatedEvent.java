package org.example.cqrssimple.domain.event;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ItemDeactivatedEvent extends IDomainEvent {
    public ItemDeactivatedEvent(String itemId) {
        super(itemId);
    }
}
