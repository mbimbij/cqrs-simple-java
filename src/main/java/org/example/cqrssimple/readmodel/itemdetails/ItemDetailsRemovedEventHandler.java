package org.example.cqrssimple.readmodel.itemdetails;

import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemRemovedEvent;
import org.example.cqrssimple.readmodel.IEventHandler;
import org.example.cqrssimple.readmodel.IReadDatabaseItemDetails;

public class ItemDetailsRemovedEventHandler extends IEventHandler<ItemRemovedEvent, IReadDatabaseItemDetails> {

    public ItemDetailsRemovedEventHandler(IReadDatabaseItemDetails readDatabase) {
        super(readDatabase);
    }

    @Override
    public void handle(ItemRemovedEvent itemRemovedEvent) {
        readDatabase.remove(itemRemovedEvent.getItemId().toString(), itemRemovedEvent.getQuantity());
    }

    @Override
    public boolean accept(Event event) {
        return event instanceof ItemRemovedEvent;
    }
}
