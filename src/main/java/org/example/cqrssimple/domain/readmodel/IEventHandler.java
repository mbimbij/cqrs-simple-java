package org.example.cqrssimple.domain.readmodel;

import org.example.cqrssimple.domain.event.IDomainEvent;

public abstract class IEventHandler<T extends IDomainEvent> {
    private final Class<T> acceptedEventType;

    protected IEventHandler(Class<T> acceptedEventType) {
        this.acceptedEventType = acceptedEventType;
    }

    public abstract void handle(T domainEvent);

    public boolean accept(IDomainEvent domainEvent) {
        return acceptedEventType.isAssignableFrom(domainEvent.getClass());
    }
}
