package org.example.cqrssimple.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
public class ItemCheckedInEvent extends Event {
    int quantity;

    public ItemCheckedInEvent(UUID uuid, int quantity) {
        super(uuid);
        this.quantity = quantity;
    }
}
