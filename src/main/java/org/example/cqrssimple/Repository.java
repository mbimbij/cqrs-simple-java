package org.example.cqrssimple;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Repository {
    private final IEventStore eventStore;
    private final IEventPublisher eventPublisher;

    void save(InventoryItem inventoryItem) {
        List<Event> uncommittedChanges = inventoryItem.getUncommittedChanges();
        eventStore.store(uncommittedChanges);
        eventPublisher.publish(uncommittedChanges);
    }
}
