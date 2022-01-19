package org.example.cqrssimple.command;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
public class CheckInItemCommand extends Command {
    int quantity;

    public CheckInItemCommand(UUID uuid, int quantity) {
        super(uuid);
        this.quantity = quantity;
    }
}
