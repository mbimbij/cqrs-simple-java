package org.example.cqrssimple.readmodel;

import org.assertj.core.api.Assertions;
import org.example.cqrssimple.domain.IDomainEvent;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class EventHandlerShould {
    @Test
    void acceptEventOfExactType() {
        // GIVEN
        Event1 event1 = new Event1(UUID.randomUUID().toString());
        EventHandler1 eventHandler1 = new EventHandler1();

        // WHEN
        boolean eventAccepted = eventHandler1.accept(event1);

        // THEN
        assertThat(eventAccepted).isTrue();
    }

    private class EventHandler1 extends IEventHandler<Event1> {
        protected EventHandler1() {
            super(Event1.class);
        }

        @Override
        void handle(IDomainEvent domainEvent) {

        }
    }

    private class Event1 extends IDomainEvent {
        public Event1(String itemId) {
            super(itemId);
        }
    }
}