package org.example.cqrssimple.event;

import org.example.cqrssimple.Message;

import java.util.UUID;

public abstract class Event extends Message {
    public Event(UUID itemId) {
        super(itemId);
    }
}
