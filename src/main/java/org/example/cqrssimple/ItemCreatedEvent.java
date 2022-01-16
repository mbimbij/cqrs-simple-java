package org.example.cqrssimple;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
public class ItemCreatedEvent extends Event {
    String itemName;

    public ItemCreatedEvent(UUID uuid, String itemName) {
        super(uuid);
        this.itemName = itemName;
    }
}
