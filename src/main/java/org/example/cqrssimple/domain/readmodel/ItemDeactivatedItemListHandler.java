package org.example.cqrssimple.domain.readmodel;

import org.example.cqrssimple.adapter.out.InMemoryFakeReadDatabaseItemList;
import org.example.cqrssimple.domain.event.ItemDeactivatedEvent;

public class ItemDeactivatedItemListHandler extends IEventHandler<ItemDeactivatedEvent> {
    private InMemoryFakeReadDatabaseItemList readDatabase;

    public ItemDeactivatedItemListHandler(InMemoryFakeReadDatabaseItemList readDatabase) {
        super(ItemDeactivatedEvent.class);
        this.readDatabase = readDatabase;
    }

    @Override
    public void handle(ItemDeactivatedEvent event) {
        readDatabase.handle(event);
    }
}
