package org.example.cqrssimple.readmodel.itemdetails;

import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemCheckedInEvent;
import org.example.cqrssimple.readmodel.IEventHandler;
import org.example.cqrssimple.readmodel.IReadDatabaseItemDetails;

public class ItemDetailsCheckedInEventHandler extends IEventHandler<ItemCheckedInEvent, IReadDatabaseItemDetails> {

    public ItemDetailsCheckedInEventHandler(IReadDatabaseItemDetails readDatabase) {
        super(readDatabase);
    }

    @Override
    public void handle(ItemCheckedInEvent itemCheckedInEvent) {
        readDatabase.checkIn(itemCheckedInEvent.getItemId().toString(), itemCheckedInEvent.getQuantity());
    }

    @Override
    public boolean accept(Event event) {
        return event instanceof ItemCheckedInEvent;
    }
}
