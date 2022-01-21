package org.example.cqrssimple.readmodel;

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

    @Test
    void notAcceptEventOfACompletelyOtherType() {
        // GIVEN
        Event2 event1 = new Event2(UUID.randomUUID().toString());
        EventHandler1 eventHandler1 = new EventHandler1();

        // WHEN
        boolean eventAccepted = eventHandler1.accept(event1);

        // THEN
        assertThat(eventAccepted).isFalse();
    }

    @Test
    void acceptEventOfASubType() {
        // GIVEN
        SubEvent1 event = new SubEvent1(UUID.randomUUID().toString());
        EventHandler1 eventHandler1 = new EventHandler1();

        // WHEN
        boolean eventAccepted = eventHandler1.accept(event);

        // THEN
        assertThat(eventAccepted).isTrue();
    }

    @Test
    void notAcceptEventOfASuperType() {
        // GIVEN
        Event1 event = new Event1(UUID.randomUUID().toString());
        SubEventHandler1 eventHandler1 = new SubEventHandler1();

        // WHEN
        boolean eventAccepted = eventHandler1.accept(event);

        // THEN
        assertThat(eventAccepted).isFalse();
    }

    private class EventHandler1 extends IEventHandler<Event1> {
        protected EventHandler1() {
            super(Event1.class);
        }

        @Override
        void handle(IDomainEvent domainEvent) {

        }
    }

    private class SubEventHandler1 extends IEventHandler<SubEvent1> {
        protected SubEventHandler1() {
            super(SubEvent1.class);
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

    private class SubEvent1 extends Event1 {
        public SubEvent1(String itemId) {
            super(itemId);
        }
    }

    private class Event2 extends IDomainEvent {
        public Event2(String itemId) {
            super(itemId);
        }

    }
}
