package org.example.cqrssimple.domain.aggregate;

import org.example.cqrssimple.domain.event.IDomainEvent;
import org.example.cqrssimple.domain.event.IEventBus;

import java.util.ArrayList;
import java.util.Collection;

public class CheckInItemCommandHandler extends ICommandHandler<CheckInItemCommand> {
    private final IEventStore eventStore;
    private final IEventBus eventBus;

    public CheckInItemCommandHandler(IEventStore eventStore, IEventBus eventBus) {
        super(CheckInItemCommand.class);
        this.eventStore = eventStore;
        this.eventBus = eventBus;
    }

    @Override
    public void handle(CheckInItemCommand command) {
        Collection<IDomainEvent> events = eventStore.getEventsForItem(command.getItemId());
        InventoryItem item = InventoryItem.buildFromHistory(events);

        ArrayList<IDomainEvent> changes = new ArrayList<>();
        item.checkIn(command.getQuantity(), changes);

        eventStore.save(changes);
        eventBus.send(changes);
    }
}
