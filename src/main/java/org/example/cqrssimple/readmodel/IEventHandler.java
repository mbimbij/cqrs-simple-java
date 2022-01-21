package org.example.cqrssimple.readmodel;

import org.example.cqrssimple.domain.IDomainEvent;

public abstract class IEventHandler<T extends IDomainEvent> {
    private final Class<T> acceptedEventType;

    protected IEventHandler(Class<T> acceptedEventType) {
        this.acceptedEventType = acceptedEventType;
    }

    abstract void handle(IDomainEvent domainEvent);

    boolean accept(IDomainEvent domainEvent) {
        return acceptedEventType.isAssignableFrom(domainEvent.getClass());
    }
}
