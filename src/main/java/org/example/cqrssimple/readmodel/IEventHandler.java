package org.example.cqrssimple.readmodel;

import lombok.RequiredArgsConstructor;
import org.example.cqrssimple.event.Event;


@RequiredArgsConstructor
public abstract class IEventHandler<TEvent extends Event, TReadDatabase> {
    protected final TReadDatabase readDatabase;
    public abstract void handle(TEvent event);

    public abstract boolean accept(Event event);
}
