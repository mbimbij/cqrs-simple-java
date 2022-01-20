package org.example.cqrssimple.readmodel;

import lombok.RequiredArgsConstructor;
import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemCreatedEvent;
import org.example.cqrssimple.event.ItemDeactivatedEvent;
import org.example.cqrssimple.event.ItemRenamedEvent;

public class ItemListView extends IEventHandler<Event, IReadDatabaseItemList> {

    public ItemListView(IReadDatabaseItemList readDatabase) {
        super(readDatabase);
    }

    @Override
    public void handle(Event event) {
        if (event instanceof ItemCreatedEvent itemCreatedEvent) {
            readDatabase.save(new ItemListDto(itemCreatedEvent.getItemId().toString(), itemCreatedEvent.getItemName()));
        } else if (event instanceof ItemDeactivatedEvent itemDeactivatedEvent) {
            readDatabase.delete(itemDeactivatedEvent.getItemId().toString());
        } else if (event instanceof ItemRenamedEvent itemRenamedEvent) {
            readDatabase.rename(itemRenamedEvent.getItemId().toString(), itemRenamedEvent.getNewName());
        }
    }

    @Override
    public boolean accept(Event event) {
        return event instanceof ItemCreatedEvent
               || event instanceof ItemDeactivatedEvent
               || event instanceof ItemRenamedEvent;
    }
}
