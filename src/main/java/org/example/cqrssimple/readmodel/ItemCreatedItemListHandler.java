package org.example.cqrssimple.readmodel;

import org.example.cqrssimple.domain.IDomainEvent;
import org.example.cqrssimple.domain.ItemCreatedEvent;

public class ItemCreatedItemListHandler extends IEventHandler<ItemCreatedEvent> {
    protected ItemCreatedItemListHandler() {
        super(ItemCreatedEvent.class);
    }

    @Override
    public void handle(IDomainEvent domainEvent) {
        System.out.println();
    }
}
