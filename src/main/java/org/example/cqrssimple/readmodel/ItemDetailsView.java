package org.example.cqrssimple.readmodel;

import lombok.RequiredArgsConstructor;
import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemCreatedEvent;
import org.example.cqrssimple.event.ItemDeactivatedEvent;

@RequiredArgsConstructor
public class ItemDetailsView implements IEventHandler {

    private final IReadDatabaseItemList readDatabase;

    @Override
    public void handle(Event event) {
        if (event instanceof ItemCreatedEvent itemCreatedEvent) {
            readDatabase.save(new ItemListDto(itemCreatedEvent.getItemId().toString(), itemCreatedEvent.getItemName()));
        } else if (event instanceof ItemDeactivatedEvent itemDeactivatedEvent) {
            readDatabase.delete(itemDeactivatedEvent.getItemId().toString());
        }
    }

    @Override
    public boolean accept(Event event) {
        return event instanceof ItemCreatedEvent
               || event instanceof ItemDeactivatedEvent;
    }
}
