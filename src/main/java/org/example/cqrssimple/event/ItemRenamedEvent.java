package org.example.cqrssimple.event;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
public class ItemRenamedEvent extends Event {
    String oldName;
    String newName;

    public ItemRenamedEvent(UUID uuid, String oldName, String newName) {
        super(uuid);
        this.oldName = oldName;
        this.newName = newName;
    }
}
