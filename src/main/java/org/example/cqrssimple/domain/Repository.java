package org.example.cqrssimple.domain;

import lombok.RequiredArgsConstructor;
import org.example.cqrssimple.event.Event;

import java.util.List;

@RequiredArgsConstructor
public class Repository {
    private final IEventStore eventStore;
    private final IEventPublisher eventPublisher;

    public void save(InventoryItem inventoryItem) {
        List<Event> uncommittedChanges = inventoryItem.getUncommittedChanges();
        eventStore.store(uncommittedChanges);
        eventPublisher.publish(uncommittedChanges);
    }
}
