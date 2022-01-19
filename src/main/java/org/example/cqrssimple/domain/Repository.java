package org.example.cqrssimple.domain;

import lombok.RequiredArgsConstructor;
import org.example.cqrssimple.command.ItemRenamedEvent;
import org.example.cqrssimple.event.Event;
import org.example.cqrssimple.event.ItemCheckedInEvent;
import org.example.cqrssimple.event.ItemCreatedEvent;
import org.example.cqrssimple.event.ItemRemovedEvent;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class Repository {
    private final IEventStore eventStore;
    private final IEventPublisher eventPublisher;

    public void save(InventoryItem inventoryItem) {
        List<Event> uncommittedChanges = inventoryItem.getUncommittedChanges();
        eventStore.store(uncommittedChanges);
        eventPublisher.publish(uncommittedChanges);
    }

    public InventoryItem findById(UUID itemId) {
        List<Event> events = eventStore.findById(itemId);

        if(events.isEmpty()){
            throw new ItemNotFoundException(itemId);
        }

        InventoryItem item = new InventoryItem(itemId);

        for (Event event: events) {
            if(event instanceof ItemCreatedEvent itemCreatedEvent){
                item.apply(itemCreatedEvent);
            }else if (event instanceof ItemCheckedInEvent itemCheckedInEvent){
                item.apply(itemCheckedInEvent);
            }else if (event instanceof ItemRemovedEvent itemRemovedEvent){
                item.apply(itemRemovedEvent);
            }else if (event instanceof ItemRenamedEvent itemRenamedEvent){
                item.apply(itemRenamedEvent);
            }
        }

        return item;
    }
}
