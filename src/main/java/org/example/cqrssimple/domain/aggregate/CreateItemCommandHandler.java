package org.example.cqrssimple.domain.aggregate;

import org.example.cqrssimple.domain.event.IDomainEvent;
import org.example.cqrssimple.domain.event.IEventBus;

import java.util.ArrayList;

public class CreateItemCommandHandler extends ICommandHandler<CreateItemCommand> {
    private final IEventStore eventStore;
    private final IEventBus eventBus;

    protected CreateItemCommandHandler(IEventStore eventStore, IEventBus eventBus) {
        super(CreateItemCommand.class);
        this.eventStore = eventStore;
        this.eventBus = eventBus;
    }

    @Override
    public void handle(CreateItemCommand command) {
        ArrayList<IDomainEvent> events = new ArrayList<>();
        new InventoryItem(command.getItemId(), command.getItemName(), events);
        eventStore.save(events);
        eventBus.send(events);
    }
}
