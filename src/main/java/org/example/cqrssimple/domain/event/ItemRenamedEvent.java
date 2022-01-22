package org.example.cqrssimple.domain.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class ItemRenamedEvent extends IDomainEvent {
    String newName;

    public ItemRenamedEvent(String itemId, String newName) {
        super(itemId);
        this.newName = newName;
    }
}
