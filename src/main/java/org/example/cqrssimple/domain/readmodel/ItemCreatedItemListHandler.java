package org.example.cqrssimple.domain.readmodel;

import org.example.cqrssimple.adapter.out.InMemoryFakeReadDatabaseItemList;
import org.example.cqrssimple.domain.ItemCreatedEvent;

public class ItemCreatedItemListHandler extends IEventHandler<ItemCreatedEvent> {
    private final InMemoryFakeReadDatabaseItemList readDatabase;

    public ItemCreatedItemListHandler(InMemoryFakeReadDatabaseItemList readDatabase) {
        super(ItemCreatedEvent.class);
        this.readDatabase = readDatabase;
    }

    @Override
    public void handle(ItemCreatedEvent domainEvent) {
        readDatabase.handle(domainEvent);
    }
}
