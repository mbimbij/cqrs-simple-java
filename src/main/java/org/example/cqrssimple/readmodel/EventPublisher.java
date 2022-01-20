package org.example.cqrssimple.readmodel;

import org.example.cqrssimple.domain.IEventPublisher;
import org.example.cqrssimple.event.Event;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher implements IEventPublisher {
    private List<IEventHandler> eventHandlers = new ArrayList<>();

    @Override
    public void publish(List<Event> events) {
        events.forEach(event -> eventHandlers.stream()
                                             .filter(eventHandler -> eventHandler.accept(event))
                                             .forEach(eventHandler -> eventHandler.handle(event)));
    }

    @Override
    public void subscribe(IEventHandler<?> eventHandler) {
        eventHandlers.add(eventHandler);
    }

}
