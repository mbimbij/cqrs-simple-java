package org.example.cqrssimple.application;

import org.example.cqrssimple.adapter.out.InMemoryFakeReadDatabase;
import org.example.cqrssimple.adapter.out.InMemorySynchronousEventBus;
import org.example.cqrssimple.domain.ItemCreatedEvent;
import org.example.cqrssimple.domain.readmodel.ItemCreatedItemListHandler;
import org.example.cqrssimple.domain.readmodel.ItemListView;
import org.example.cqrssimple.domain.readmodel.SingleItemForList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReadFacadeShould {

    private InMemoryFakeReadDatabase readDatabase;
    private ReadFacade readFacade;

    @BeforeEach
    void setUp() {
        readDatabase = new InMemoryFakeReadDatabase();
        readFacade = new ReadFacade(readDatabase);
    }

    @Test
    void returnEmptyList_whenGetItemList_andNoEventYet() {
        // WHEN
        ItemListView itemListView = readFacade.getItemList();

        // THEN
        ItemListView expectedItemListView = new ItemListView(Collections.emptyList());
        assertThat(itemListView).usingRecursiveComparison()
                                .isEqualTo(expectedItemListView);
    }

    @Test
    void return2Items_whenGetItemList_and2ItemsCreated() {
        // GIVEN
        InMemoryFakeReadDatabase readDatabase = new InMemoryFakeReadDatabase();
        ItemCreatedItemListHandler itemCreatedHandler = new ItemCreatedItemListHandler(readDatabase);
        InMemorySynchronousEventBus eventBus = new InMemorySynchronousEventBus();
        eventBus.subscribe(itemCreatedHandler);

        String item1 = "item1";
        String item2 = "item2";

        eventBus.send(List.of(new ItemCreatedEvent(item1, item1),
                              new ItemCreatedEvent(item2, item2)));

        ReadFacade readFacade = new ReadFacade(readDatabase);

        // WHEN
        ItemListView itemListView = readFacade.getItemList();

        // THEN
        ItemListView expectedItemListView = new ItemListView(List.of(new SingleItemForList(item1, item1),
                                                                     new SingleItemForList(item2, item2)));
        assertThat(itemListView).usingRecursiveComparison()
                                .isEqualTo(expectedItemListView);
    }
}