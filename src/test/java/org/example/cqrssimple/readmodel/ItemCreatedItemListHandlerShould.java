package org.example.cqrssimple.readmodel;

import org.example.cqrssimple.domain.ItemCreatedEvent;
import org.example.cqrssimple.domain.ItemDeactivatedEvent;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class ItemCreatedItemListHandlerShould {
    @Test
    void name() {
        ItemCreatedItemListHandler handler = new ItemCreatedItemListHandler();
        handler.accept(new ItemCreatedEvent(UUID.randomUUID().toString(),"some name"));
        handler.accept(new ItemDeactivatedEvent(UUID.randomUUID().toString()));
    }
}