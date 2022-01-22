package org.example.cqrssimple.application;

import org.example.cqrssimple.adapter.out.InMemoryFakeReadDatabase;
import org.example.cqrssimple.adapter.out.InMemorySynchronousEventBus;
import org.example.cqrssimple.domain.ItemCreatedEvent;
import org.example.cqrssimple.domain.ItemDeactivatedEvent;
import org.example.cqrssimple.domain.readmodel.ItemCreatedItemListHandler;
import org.example.cqrssimple.domain.readmodel.ItemListView;
import org.example.cqrssimple.domain.readmodel.SingleItemForList;
import org.example.cqrssimple.domain.readmodel.ItemDeactivatedItemListHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReadFacadeShould {

    private InMemoryFakeReadDatabase readDatabase;
    private ReadFacade readFacade;
    private InMemorySynchronousEventBus eventBus;

    @BeforeEach
    void setUp() {
        readDatabase = new InMemoryFakeReadDatabase();
        readFacade = new ReadFacade(readDatabase);
        eventBus = new InMemorySynchronousEventBus();
        eventBus.subscribe(new ItemCreatedItemListHandler(readDatabase));
        eventBus.subscribe(new ItemDeactivatedItemListHandler(readDatabase));
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
        String item1 = "item1";
        String item2 = "item2";

        eventBus.send(List.of(new ItemCreatedEvent(item1, item1),
                              new ItemCreatedEvent(item2, item2)));

        // WHEN
        ItemListView itemListView = readFacade.getItemList();

        // THEN
        ItemListView expectedItemListView = new ItemListView(List.of(new SingleItemForList(item1, item1),
                                                                     new SingleItemForList(item2, item2)));
        assertThat(itemListView).usingRecursiveComparison()
                                .isEqualTo(expectedItemListView);
    }

    @Test
    void return1Item_whenGetItemList_and2ItemsCreated_and1ItemDeleted() {
        // GIVEN
        String item1 = "item1";
        String item2 = "item2";

        eventBus.send(List.of(new ItemCreatedEvent(item1, item1),
                              new ItemCreatedEvent(item2, item2),
                              new ItemDeactivatedEvent(item1)));

        // WHEN
        ItemListView itemListView = readFacade.getItemList();

        // THEN
        ItemListView expectedItemListView = new ItemListView(List.of(new SingleItemForList(item2, item2)));
        assertThat(itemListView).usingRecursiveComparison()
                                .isEqualTo(expectedItemListView);
    }
}
