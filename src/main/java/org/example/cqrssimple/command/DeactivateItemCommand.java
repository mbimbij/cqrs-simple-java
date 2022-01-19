package org.example.cqrssimple.command;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
public class DeactivateItemCommand extends Command {
    public DeactivateItemCommand(UUID itemId) {
        super(itemId);
    }
}
