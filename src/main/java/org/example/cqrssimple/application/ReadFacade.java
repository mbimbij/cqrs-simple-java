package org.example.cqrssimple.application;

import org.example.cqrssimple.adapter.out.InMemoryFakeReadDatabase;
import org.example.cqrssimple.domain.readmodel.ItemListView;

import java.util.Collections;

public class ReadFacade {
    private InMemoryFakeReadDatabase readDatabase;

    public ReadFacade(InMemoryFakeReadDatabase readDatabase) {
        this.readDatabase = readDatabase;
    }

    public ItemListView getItemList() {
        return readDatabase.getItemList();
    }
}
