package org.example.cqrssimple.domain.event;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Value
public class ItemCheckedInEvent extends IDomainEvent {
    int quantity;

    public ItemCheckedInEvent(String itemId, int quantity) {
        super(itemId);
        this.quantity = quantity;
    }
}
