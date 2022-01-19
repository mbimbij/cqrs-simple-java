package org.example.cqrssimple.command;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
public class RenameItemCommand extends Command {
    String newName;

    public RenameItemCommand(UUID uuid, String newName) {
        super(uuid);
        this.newName = newName;
    }
}
