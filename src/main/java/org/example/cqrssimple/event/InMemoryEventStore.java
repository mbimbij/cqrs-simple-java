package org.example.cqrssimple.event;

import lombok.Getter;
import org.example.cqrssimple.writemodel.IEventStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class InMemoryEventStore implements IEventStore {
    private final List<Event> events = new ArrayList<>();

    @Override
    public void store(List<Event> events) {
        this.events.addAll(events);
    }

    @Override
    public List<Event> findById(UUID itemId) {
        return events.stream()
                     .filter(event -> Objects.equals(event.getItemId(), itemId))
                     .collect(Collectors.toList());
    }
}
