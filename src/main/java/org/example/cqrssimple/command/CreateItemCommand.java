package org.example.cqrssimple.command;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
public class CreateItemCommand extends Command {
    String itemName;

    public CreateItemCommand(UUID uuid, String itemName) {
        super(uuid);
        this.itemName = itemName;
    }
}
