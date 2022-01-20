package org.example.cqrssimple.readmodel;

import org.example.cqrssimple.event.Event;

public interface IEventHandler {
    void handle(Event event);

    boolean accept(Event event);
}
