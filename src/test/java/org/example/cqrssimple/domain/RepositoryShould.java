package org.example.cqrssimple.domain;

import org.example.cqrssimple.event.InMemoryEventStore;
import org.example.cqrssimple.event.ItemCheckedInEvent;
import org.example.cqrssimple.event.ItemCreatedEvent;
import org.example.cqrssimple.event.ItemRemovedEvent;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class RepositoryShould {
    @Test
    void applyItemRemovedEvents() {
        // GIVEN
        UUID uuid = UUID.randomUUID();
        String itemName = "item name";

        InMemoryEventStore inMemoryEventStore = new InMemoryEventStore();
        inMemoryEventStore.store(List.of(
                new ItemCreatedEvent(uuid, itemName),
                new ItemCheckedInEvent(uuid, 5),
                new ItemRemovedEvent(uuid, 3)
        ));

        Repository repository = new Repository(inMemoryEventStore, mock(IEventPublisher.class));

        // WHEN
        InventoryItem item = repository.findById(uuid);

        // THEN
        assertThat(item.getQuantity()).isEqualTo(2);
    }
}