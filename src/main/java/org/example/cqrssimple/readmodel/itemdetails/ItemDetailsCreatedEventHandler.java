package org.example.cqrssimple.readmodel.itemdetails;

import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemCreatedEvent;
import org.example.cqrssimple.readmodel.IEventHandler;
import org.example.cqrssimple.readmodel.IReadDatabaseItemDetails;
import org.example.cqrssimple.readmodel.ItemDetailsDto;

public class ItemDetailsCreatedEventHandler extends IEventHandler<ItemCreatedEvent, IReadDatabaseItemDetails> {
    public ItemDetailsCreatedEventHandler(IReadDatabaseItemDetails readDatabase) {
        super(readDatabase);
    }

    @Override
    public void handle(ItemCreatedEvent itemCreatedEvent) {
        readDatabase.save(new ItemDetailsDto(itemCreatedEvent.getItemId().toString(), itemCreatedEvent.getItemName(), 0));
    }

    @Override
    public boolean accept(Event event) {
        return event instanceof ItemCreatedEvent;
    }
}
