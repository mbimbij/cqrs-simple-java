package org.example.cqrssimple.domain.readmodel;

import org.example.cqrssimple.domain.event.IReadDatabaseItemList;
import org.example.cqrssimple.domain.event.ItemRenamedEvent;

public class ItemRenamedItemListHandler extends IEventHandler<ItemRenamedEvent> {
    private final IReadDatabaseItemList readDatabase;

    public ItemRenamedItemListHandler(IReadDatabaseItemList readDatabase) {
        super(ItemRenamedEvent.class);
        this.readDatabase = readDatabase;
    }

    @Override
    public void handle(ItemRenamedEvent domainEvent) {
        readDatabase.handle(domainEvent);
    }
}
