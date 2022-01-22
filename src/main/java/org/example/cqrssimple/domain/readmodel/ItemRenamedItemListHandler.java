package org.example.cqrssimple.domain.readmodel;

import org.example.cqrssimple.domain.IReadDatabase;
import org.example.cqrssimple.domain.ItemRenamedEvent;

public class ItemRenamedItemListHandler extends IEventHandler<ItemRenamedEvent> {
    private final IReadDatabase readDatabase;

    public ItemRenamedItemListHandler(IReadDatabase readDatabase) {
        super(ItemRenamedEvent.class);
        this.readDatabase = readDatabase;
    }

    @Override
    public void handle(ItemRenamedEvent domainEvent) {
        readDatabase.handle(domainEvent);
    }
}
