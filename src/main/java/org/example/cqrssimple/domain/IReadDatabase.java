package org.example.cqrssimple.domain;

import org.example.cqrssimple.domain.readmodel.ItemListView;

public interface IReadDatabase {
    ItemListView getItemList();

    void handle(ItemCreatedEvent itemCreatedEvent);
}
