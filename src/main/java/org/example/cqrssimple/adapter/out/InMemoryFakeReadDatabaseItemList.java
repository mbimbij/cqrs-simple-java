package org.example.cqrssimple.adapter.out;

import org.example.cqrssimple.domain.event.IReadDatabaseItemList;
import org.example.cqrssimple.domain.event.ItemCreatedEvent;
import org.example.cqrssimple.domain.event.ItemDeactivatedEvent;
import org.example.cqrssimple.domain.event.ItemRenamedEvent;
import org.example.cqrssimple.domain.readmodel.ItemListView;
import org.example.cqrssimple.domain.readmodel.SingleItemForList;

import java.util.SortedMap;
import java.util.TreeMap;

public class InMemoryFakeReadDatabaseItemList implements IReadDatabaseItemList {
    private final SortedMap<String, SingleItemForList> items = new TreeMap<>();

    @Override
    public ItemListView getItemList() {
        return new ItemListView(items.values());
    }

    @Override
    public void handle(ItemCreatedEvent event) {
        String itemId = event.getItemId();
        items.put(itemId, new SingleItemForList(itemId, event.getItemName()));
    }

    @Override
    public void handle(ItemRenamedEvent event) {
        String itemId = event.getItemId();
        items.remove(itemId);
        items.put(itemId, new SingleItemForList(itemId, event.getNewName()));
    }

    @Override
    public void handle(ItemDeactivatedEvent event) {
        items.remove(event.getItemId());
    }

}
