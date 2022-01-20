package org.example.cqrssimple.readmodel;

import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemCheckedInEvent;
import org.example.cqrssimple.event.ItemCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class EventPublisherShould {

    private final String itemName = "item name";
    private final EventPublisher eventPublisher = new EventPublisher();
    private UUID uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
    }

    @Test
    void callTheAppropriateEventHandler() {
        // GIVEN

        FakeDatabaseItemDetails readDatabase = new FakeDatabaseItemDetails();
        ItemCreatedEventHandler itemCreatedEventHandler = spy(new ItemCreatedEventHandler(readDatabase));
        ItemCheckedInEventHandler itemCheckedInEventHandler = spy(new ItemCheckedInEventHandler(readDatabase));
        eventPublisher.subscribe(itemCreatedEventHandler);
        eventPublisher.subscribe(itemCheckedInEventHandler);

        // WHEN
        eventPublisher.publish(List.of(new ItemCreatedEvent(uuid, itemName)));

        // THEN
        verify(itemCreatedEventHandler).handle(any(ItemCreatedEvent.class));
        verify(itemCheckedInEventHandler, never()).handle(any());
    }

    public static class ItemCheckedInEventHandler extends IEventHandler<ItemCheckedInEvent, IReadDatabaseItemDetails> {
        public ItemCheckedInEventHandler(IReadDatabaseItemDetails readDatabase) {
            super(readDatabase);
        }

        @Override
        public void handle(ItemCheckedInEvent event) {

        }

        @Override
        public boolean accept(Event event) {
            return event instanceof ItemCheckedInEvent;
        }
    }

    public static class ItemCreatedEventHandler extends IEventHandler<ItemCreatedEvent, IReadDatabaseItemDetails> {
        public ItemCreatedEventHandler(IReadDatabaseItemDetails readDatabase) {
            super(readDatabase);
        }

        @Override
        public void handle(ItemCreatedEvent event) {

        }

        @Override
        public boolean accept(Event event) {
            return event instanceof ItemCreatedEvent;
        }
    }
}