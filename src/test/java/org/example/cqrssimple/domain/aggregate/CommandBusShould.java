package org.example.cqrssimple.domain.aggregate;

import org.assertj.core.api.SoftAssertions;
import org.example.cqrssimple.adapter.out.InMemoryEventStore;
import org.example.cqrssimple.adapter.out.MockEventBus;
import org.example.cqrssimple.domain.event.ItemCheckedInEvent;
import org.example.cqrssimple.domain.event.ItemCreatedEvent;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public class CommandBusShould {

    @Test
    void sendItemCreatedEvent_whenSendCreateItemCommand() {
        // GIVEN
        CommandBus commandBus = new CommandBus();
        MockEventBus eventBus = new MockEventBus();
        InMemoryEventStore eventStore = new InMemoryEventStore();
        commandBus.register(new CreateItemCommandHandler(eventStore, eventBus));

        String itemId = UUID.randomUUID().toString();
        String itemName = "item name";

        // WHEN
        commandBus.send(new CreateItemCommand(itemId, itemName));

        // THEN
        SoftAssertions.assertSoftly(softAssertions -> {
            ItemCreatedEvent expectedEvent = new ItemCreatedEvent(itemId, itemName);
            softAssertions.assertThat(eventBus.getSentEvents()).containsExactly(expectedEvent);
            softAssertions.assertThat(eventStore.getEvents()).containsExactly(expectedEvent);
        });
    }

    @Test
    void sendItemCheckIn_whenSendCheckInItemCommand() {
        // GIVEN
        CommandBus commandBus = new CommandBus();
        MockEventBus eventBus = new MockEventBus();
        InMemoryEventStore eventStore = new InMemoryEventStore();
        commandBus.register(new CheckInItemCommandHandler(eventStore, eventBus));

        String itemId = UUID.randomUUID().toString();
        String itemName = "item name";
        int quantity = 2;

        eventStore.save(List.of(new ItemCreatedEvent(itemId, itemName)));

        // WHEN
        commandBus.send(new CheckInItemCommand(itemId, quantity));

        // THEN
        SoftAssertions.assertSoftly(softAssertions -> {
            ItemCheckedInEvent expectedEvent = new ItemCheckedInEvent(itemId, quantity);
            softAssertions.assertThat(eventBus.getSentEvents()).contains(expectedEvent);
            softAssertions.assertThat(eventStore.getEvents()).contains(expectedEvent);
        });
    }
}
