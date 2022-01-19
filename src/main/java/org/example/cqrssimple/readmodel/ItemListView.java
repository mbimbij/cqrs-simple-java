package org.example.cqrssimple.readmodel;

import lombok.RequiredArgsConstructor;
import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemCreatedEvent;

@RequiredArgsConstructor
public class ItemListView implements IEventHandler<ItemCreatedEvent> {

    private final IReadDatabase readDatabase;

    @Override
    public void handle(ItemCreatedEvent event) {
        readDatabase.save(new ItemListDto(event.getItemId().toString(), event.getItemName()));
    }

    @Override
    public boolean accept(Event event) {
        return event instanceof ItemCreatedEvent;
    }
}
