package org.example.cqrssimple.command;

import org.example.cqrssimple.Message;

import java.util.UUID;

public abstract class Command extends Message {
    public Command(UUID itemId) {
        super(itemId);
    }
}
