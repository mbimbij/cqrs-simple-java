package org.example.cqrssimple.domain.event;

import org.example.cqrssimple.domain.readmodel.ItemListView;

public interface IReadDatabaseItemList {
    ItemListView getItemList();

    void handle(ItemCreatedEvent itemCreatedEvent);

    void handle(ItemRenamedEvent domainEvent);

    void handle(ItemDeactivatedEvent itemId);
}
