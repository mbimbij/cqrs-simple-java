package org.example.cqrssimple.readmodel.itemdetails;

import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemDeactivatedEvent;
import org.example.cqrssimple.readmodel.IEventHandler;
import org.example.cqrssimple.readmodel.IReadDatabaseItemDetails;

public class ItemDetailsDeactivatedEventHandler extends IEventHandler<ItemDeactivatedEvent, IReadDatabaseItemDetails> {

    public ItemDetailsDeactivatedEventHandler(IReadDatabaseItemDetails readDatabase) {
        super(readDatabase);
    }

    @Override
    public void handle(ItemDeactivatedEvent itemDeactivatedEvent) {
        readDatabase.deactivate(itemDeactivatedEvent.getItemId().toString());
    }

    @Override
    public boolean accept(Event event) {
        return event instanceof ItemDeactivatedEvent;
    }
}
