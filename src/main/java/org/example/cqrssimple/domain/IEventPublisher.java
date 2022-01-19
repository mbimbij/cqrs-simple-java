package org.example.cqrssimple.domain;

import org.example.cqrssimple.event.Event;

import java.util.List;

public interface IEventPublisher {
    void publish(List<Event> events);
}
