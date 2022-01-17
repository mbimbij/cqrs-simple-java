package org.example.cqrssimple.domain;

import org.example.cqrssimple.event.Event;

import java.util.List;

public interface IEventStore {
    void store(List<Event> events);
}
