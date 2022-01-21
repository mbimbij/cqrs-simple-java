package org.example.cqrssimple;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class ItemRemovedEvent extends IDomainEvent {
    int quantity;

    public ItemRemovedEvent(String itemId, int quantity) {
        super(itemId);
        this.quantity = quantity;
    }
}
