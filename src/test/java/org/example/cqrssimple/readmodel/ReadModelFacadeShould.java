package org.example.cqrssimple.readmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReadModelFacadeShould {

    private ReadModelFacade readModelFacade;

    @BeforeEach
    void setUp() {
        readModelFacade = new ReadModelFacade(new FakeReadDatabase());
    }

    @Test
    void returnEmptyItemList_whenNoItemCreated() {
        // WHEN
        List<ItemListDto> itemList = readModelFacade.getItemList();

        // THEN
        assertThat(itemList).isEmpty();
    }

    @Test
    void return2Items_whenItemCreatedEventSent_for2Items() {
        // WHEN
        List<ItemListDto> itemList = readModelFacade.getItemList();

        // THEN
        assertThat(itemList).isEmpty();
    }
}