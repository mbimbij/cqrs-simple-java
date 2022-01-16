package org.example.cqrssimple;

import java.util.UUID;

public abstract class Event extends Message {
    public Event(UUID itemId) {
        super(itemId);
    }
}
