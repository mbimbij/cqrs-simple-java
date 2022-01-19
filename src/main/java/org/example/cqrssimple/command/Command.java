package org.example.cqrssimple.command;

import lombok.EqualsAndHashCode;
import org.example.cqrssimple.Message;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
public abstract class Command extends Message {
    public Command(UUID itemId) {
        super(itemId);
    }
}
