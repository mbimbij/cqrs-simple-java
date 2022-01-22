package org.example.cqrssimple.domain.event;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Value
public class ItemRemovedEvent extends IDomainEvent {
    int quantity;

    public ItemRemovedEvent(String itemId, int quantity) {
        super(itemId);
        this.quantity = quantity;
    }
}
