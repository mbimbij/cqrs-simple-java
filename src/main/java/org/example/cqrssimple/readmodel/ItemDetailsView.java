package org.example.cqrssimple.readmodel;

import lombok.RequiredArgsConstructor;
import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemCheckedInEvent;
import org.example.cqrssimple.event.ItemCreatedEvent;
import org.example.cqrssimple.event.ItemDeactivatedEvent;
import org.example.cqrssimple.event.ItemRemovedEvent;
import org.example.cqrssimple.event.ItemRenamedEvent;

@RequiredArgsConstructor
public class ItemDetailsView implements IEventHandler {

    private final IReadDatabaseItemDetails readDatabase;

    @Override
    public void handle(Event event) {
        if (event instanceof ItemCreatedEvent itemCreatedEvent) {
            readDatabase.save(new ItemDetailsDto(itemCreatedEvent.getItemId().toString(), itemCreatedEvent.getItemName(), 0));
        } else if (event instanceof ItemCheckedInEvent itemCheckedInEvent) {
            readDatabase.checkIn(itemCheckedInEvent.getItemId().toString(), itemCheckedInEvent.getQuantity());
        } else if (event instanceof ItemRemovedEvent itemRemovedEvent) {
            readDatabase.remove(itemRemovedEvent.getItemId().toString(), itemRemovedEvent.getQuantity());
        } else if (event instanceof ItemRenamedEvent itemRenamedEvent) {
            readDatabase.rename(itemRenamedEvent.getItemId().toString(), itemRenamedEvent.getNewName());
        } else if (event instanceof ItemDeactivatedEvent itemDeactivatedEvent) {
            readDatabase.deactivate(itemDeactivatedEvent.getItemId().toString());
        }
    }

    @Override
    public boolean accept(Event event) {
        return event instanceof ItemCreatedEvent
               || event instanceof ItemCheckedInEvent
               || event instanceof ItemRemovedEvent
               || event instanceof ItemRenamedEvent
               || event instanceof ItemDeactivatedEvent;
    }
}
