package org.example.cqrssimple;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class ItemCreatedEvent extends ItemEvent {
    String itemName;

    public ItemCreatedEvent(String itemId, String itemName) {
        super(itemId);
        this.itemName = itemName;
    }
}
