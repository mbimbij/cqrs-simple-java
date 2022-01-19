package org.example.cqrssimple.readmodel;

import lombok.RequiredArgsConstructor;
import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemCreatedEvent;

@RequiredArgsConstructor
public class ItemDetailsView implements IEventHandler {

    private final IReadDatabaseItemDetails readDatabase;

    @Override
    public void handle(Event event) {
        if (event instanceof ItemCreatedEvent itemCreatedEvent) {
            readDatabase.save(new ItemDetailsDto(itemCreatedEvent.getItemId().toString(), itemCreatedEvent.getItemName(), 0));
        }
    }

    @Override
    public boolean accept(Event event) {
        return event instanceof ItemCreatedEvent;
    }
}
