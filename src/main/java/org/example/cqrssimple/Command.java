package org.example.cqrssimple;

import java.util.UUID;

public abstract class Command extends Message {
    public Command(UUID itemId) {
        super(itemId);
    }
}
