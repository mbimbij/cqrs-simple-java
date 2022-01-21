package org.example.cqrssimple.domain;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class ItemCreatedEvent extends IDomainEvent {
    String itemName;

    public ItemCreatedEvent(String itemId, String itemName) {
        super(itemId);
        this.itemName = itemName;
    }
}
