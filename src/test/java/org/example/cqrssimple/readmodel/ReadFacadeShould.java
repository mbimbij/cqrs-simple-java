package org.example.cqrssimple.readmodel;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class ReadFacadeShould {
    @Test
    void returnEmptyList_whenGetItemList_andNoEventYet() {
        // GIVEN
        ReadFacade readFacade = new ReadFacade();

        // WHEN
        Collection<ItemListDto> itemList = readFacade.getItemList();

        // THEN
        assertThat(itemList).isEmpty();
    }
}