package org.example.cqrssimple.readmodel;

import org.example.cqrssimple.event.Event;

public interface IEventHandler<T extends Event> {
    void handle(T event);

    boolean accept(Event event);
}
