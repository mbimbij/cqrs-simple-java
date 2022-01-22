package org.example.cqrssimple.application;

import org.example.cqrssimple.adapter.out.InMemoryFakeReadDatabaseItemList;
import org.example.cqrssimple.domain.readmodel.ItemListView;

public class ReadFacade {
    private InMemoryFakeReadDatabaseItemList readDatabase;

    public ReadFacade(InMemoryFakeReadDatabaseItemList readDatabase) {
        this.readDatabase = readDatabase;
    }

    public ItemListView getItemList() {
        return readDatabase.getItemList();
    }
}
