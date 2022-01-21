package org.example.cqrssimple;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class ItemCheckedInEvent extends ItemEvent {
    int quantity;

    public ItemCheckedInEvent(String itemId, int quantity) {
        super(itemId);
        this.quantity = quantity;
    }
}
