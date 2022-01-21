package org.example.cqrssimple;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class ItemRenamedEvent extends ItemEvent {
    String newName;

    public ItemRenamedEvent(String itemId, String newName) {
        super(itemId);
        this.newName = newName;
    }
}
