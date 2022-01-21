package org.example.cqrssimple.adapter.out;

import org.example.cqrssimple.domain.IDomainEvent;
import org.example.cqrssimple.domain.IEventBus;
import org.example.cqrssimple.domain.readmodel.IEventHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InMemorySynchronousEventBus implements IEventBus {
    private List<IEventHandler> eventHandlers = new ArrayList<>();

    @Override
    public void send(Collection<IDomainEvent> domainEvents) {
        domainEvents.forEach(domainEvent -> eventHandlers.stream()
                                                         .filter(eventHandler -> eventHandler.accept(domainEvent))
                                                         .forEach(eventHandler -> eventHandler.handle(domainEvent)));
    }

    @Override
    public void subscribe(IEventHandler<?> eventHandler) {
        eventHandlers.add(eventHandler);
    }
}
