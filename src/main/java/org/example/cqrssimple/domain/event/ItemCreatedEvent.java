package org.example.cqrssimple.domain.event;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Value
public class ItemCreatedEvent extends IDomainEvent {
    String itemName;

    public ItemCreatedEvent(String itemId, String itemName) {
        super(itemId);
        this.itemName = itemName;
    }
}
