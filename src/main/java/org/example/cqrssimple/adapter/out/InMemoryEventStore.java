package org.example.cqrssimple.adapter.out;

import lombok.Getter;
import org.example.cqrssimple.domain.aggregate.IEventStore;
import org.example.cqrssimple.domain.event.IDomainEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class InMemoryEventStore implements IEventStore {
    private final List<IDomainEvent> events = new ArrayList<>();

    @Override
    public Collection<IDomainEvent> getEventsForItem(String itemId) {
        return events.stream()
                     .filter(iDomainEvent -> Objects.equals(itemId, iDomainEvent.getItemId()))
                     .collect(Collectors.toList());
    }

    @Override
    public void save(Collection<IDomainEvent> events) {
        this.events.addAll(events);
    }
}
