package org.example.cqrssimple.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
public class ItemRemovedEvent extends Event {
    int quantity;

    public ItemRemovedEvent(UUID itemId, int quantity) {
        super(itemId);
        this.quantity = quantity;
    }
}
