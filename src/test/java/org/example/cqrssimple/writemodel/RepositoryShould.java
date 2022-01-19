package org.example.cqrssimple.writemodel;

import org.example.cqrssimple.event.ItemDeactivatedEvent;
import org.example.cqrssimple.event.ItemRenamedEvent;
import org.example.cqrssimple.event.InMemoryEventStore;
import org.example.cqrssimple.event.ItemCheckedInEvent;
import org.example.cqrssimple.event.ItemCreatedEvent;
import org.example.cqrssimple.event.ItemRemovedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class RepositoryShould {

    private final String itemName = "item name";
    private UUID uuid;
    private InMemoryEventStore inMemoryEventStore;
    private Repository repository;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        inMemoryEventStore = new InMemoryEventStore();
        repository = new Repository(inMemoryEventStore, mock(IEventPublisher.class));
    }

    @Test
    void applyItemRemovedEvents() {
        // GIVEN
        inMemoryEventStore.store(List.of(
                new ItemCreatedEvent(uuid, itemName),
                new ItemCheckedInEvent(uuid, 5),
                new ItemRemovedEvent(uuid, 3)
        ));

        // WHEN
        InventoryItem item = repository.findById(uuid);

        // THEN
        assertThat(item.getQuantity()).isEqualTo(2);
    }

    @Test
    void applyItemRenamedEvent() {
        // GIVEN
        String newName = "new name";
        inMemoryEventStore.store(List.of(
                new ItemCreatedEvent(uuid, itemName),
                new ItemRenamedEvent(uuid, itemName, newName)
        ));

        // WHEN
        InventoryItem item = repository.findById(uuid);

        // THEN
        assertThat(item.getName()).isEqualTo(newName);
    }

    @Test
    void applyItemDeactivatedEvent() {
        // GIVEN
        inMemoryEventStore.store(List.of(
                new ItemCreatedEvent(uuid, itemName),
                new ItemDeactivatedEvent(uuid)
        ));

        // WHEN
        InventoryItem item = repository.findById(uuid);

        // THEN
        assertThat(item.isActivated()).isFalse();
    }
}