package org.example.cqrssimple.readmodel;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReadModelFacadeShould {
    @Test
    void returnEmptyItemList_whenNoItemCreated() {
        // GIVEN
        ReadModelFacade readModelFacade = new ReadModelFacade();

        // WHEN
        List<ItemListDto> itemList = readModelFacade.getItemList();

        // THEN
        assertThat(itemList).isEmpty();
    }
}