package org.example.cqrssimple;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
public class CreateItemCommand extends Command {
    String itemName;

    public CreateItemCommand(UUID randomUUID, String itemName) {
        super(randomUUID);
        this.itemName = itemName;
    }
}
