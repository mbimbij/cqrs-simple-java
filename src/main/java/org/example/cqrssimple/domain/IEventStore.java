package org.example.cqrssimple.domain;

import org.example.cqrssimple.event.Event;

import java.util.List;
import java.util.UUID;

public interface IEventStore {
    void store(List<Event> events);

    List<Event> findById(UUID itemId);
}
