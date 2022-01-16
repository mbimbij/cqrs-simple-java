package org.example.cqrssimple;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class InMemoryEventStore implements IEventStore {
    private final List<Event> events = new ArrayList<>();

    @Override
    public void store(List<Event> events) {
        this.events.addAll(events);
    }
}
