package org.example.cqrssimple.readmodel.itemdetails;

import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemRenamedEvent;
import org.example.cqrssimple.readmodel.IEventHandler;
import org.example.cqrssimple.readmodel.IReadDatabaseItemDetails;

public class ItemDetailsRenamedEventHandler extends IEventHandler<ItemRenamedEvent, IReadDatabaseItemDetails> {

    public ItemDetailsRenamedEventHandler(IReadDatabaseItemDetails readDatabase) {
        super(readDatabase);
    }

    @Override
    public void handle(ItemRenamedEvent itemRenamedEvent) {
        readDatabase.rename(itemRenamedEvent.getItemId().toString(), itemRenamedEvent.getNewName());
    }

    @Override
    public boolean accept(Event event) {
        return event instanceof ItemRenamedEvent;
    }
}
