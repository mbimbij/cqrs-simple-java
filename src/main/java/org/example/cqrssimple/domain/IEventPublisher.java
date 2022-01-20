package org.example.cqrssimple.domain;

import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.readmodel.IEventHandler;

import java.util.List;

public interface IEventPublisher {
    void publish(List<Event> events);
    void subscribe(IEventHandler eventHandler);
}
