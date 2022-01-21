package org.example.cqrssimple.domain;

import org.example.cqrssimple.domain.readmodel.IEventHandler;

import java.util.Collection;

public interface IEventBus {
    void send(Collection<IDomainEvent> domainEvents);

    void subscribe(IEventHandler<?> eventHandler);
}
