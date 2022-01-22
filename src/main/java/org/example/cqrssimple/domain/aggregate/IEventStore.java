package org.example.cqrssimple.domain.aggregate;

import org.example.cqrssimple.domain.event.IDomainEvent;

import java.util.Collection;

public interface IEventStore {
    Collection<IDomainEvent> getEventsForItem(String itemId);

    void save(Collection<IDomainEvent> events);
}
