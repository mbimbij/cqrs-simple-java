package org.example.cqrssimple.event;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
public class ItemDeactivatedEvent extends Event {
    public ItemDeactivatedEvent(UUID itemId) {
        super(itemId);
    }
}
