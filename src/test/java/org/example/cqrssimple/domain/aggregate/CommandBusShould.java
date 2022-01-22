package org.example.cqrssimple.domain.aggregate;

import org.assertj.core.api.SoftAssertions;
import org.example.cqrssimple.adapter.out.InMemoryEventStore;
import org.example.cqrssimple.adapter.out.MockEventBus;
import org.example.cqrssimple.domain.event.ItemCreatedEvent;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CommandBusShould {

    @Test
    void sendItemCreatedEvent_whenSendCreateItemCommand() {
        // GIVEN
        CommandBus commandBus = new CommandBus();
        MockEventBus eventBus = new MockEventBus();
        InMemoryEventStore eventStore = new InMemoryEventStore();
        commandBus.register(new CreateItemCommandHandler(eventStore,eventBus));

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
}
