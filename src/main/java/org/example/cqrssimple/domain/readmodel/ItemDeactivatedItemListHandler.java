package org.example.cqrssimple.domain.readmodel;

import org.example.cqrssimple.adapter.out.InMemoryFakeReadDatabase;
import org.example.cqrssimple.domain.ItemDeactivatedEvent;
import org.example.cqrssimple.domain.readmodel.IEventHandler;

public class ItemDeactivatedItemListHandler extends IEventHandler<ItemDeactivatedEvent> {
    private InMemoryFakeReadDatabase readDatabase;

    public ItemDeactivatedItemListHandler(InMemoryFakeReadDatabase readDatabase) {
        super(ItemDeactivatedEvent.class);
        this.readDatabase = readDatabase;
    }

    @Override
    public void handle(ItemDeactivatedEvent event) {
        readDatabase.deactivate(event.getItemId());
    }
}
