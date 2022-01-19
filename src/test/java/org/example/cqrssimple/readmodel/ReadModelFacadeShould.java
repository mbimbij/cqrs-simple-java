package org.example.cqrssimple.readmodel;

import org.example.cqrssimple.event.ItemCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ReadModelFacadeShould {

    private ReadModelFacade readModelFacade;
    private EventPublisher eventPublisher;

    @BeforeEach
    void setUp() {
        FakeReadDatabase fakeReadDatabase = new FakeReadDatabase();
        readModelFacade = new ReadModelFacade(fakeReadDatabase);
        eventPublisher = new EventPublisher();
        eventPublisher.subscribe(new ItemListView(fakeReadDatabase));
    }

    @Test
    void returnEmptyItemList_whenNoItemCreated() {
        // WHEN
        Collection<ItemListDto> itemList = readModelFacade.getItemList();

        // THEN
        assertThat(itemList).isEmpty();
    }

    @Test
    void return2Items_whenItemCreatedEventSent_for2Items() {
        // GIVEN
        UUID uuid1 = UUID.randomUUID();
        String itemName1 = "item1";
        UUID uuid2 = UUID.randomUUID();
        String itemName2 = "item2";

        eventPublisher.publish(List.of(
                new ItemCreatedEvent(uuid1, itemName1),
                new ItemCreatedEvent(uuid2, itemName2)
        ));

        // WHEN
        Collection<ItemListDto> itemList = readModelFacade.getItemList();

        // THEN
        assertThat(itemList).containsExactly(
                new ItemListDto(uuid1.toString(), itemName1),
                new ItemListDto(uuid2.toString(), itemName2)
        );
    }
}