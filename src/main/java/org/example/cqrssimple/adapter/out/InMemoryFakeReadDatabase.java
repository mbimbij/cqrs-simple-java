package org.example.cqrssimple.adapter.out;

import org.example.cqrssimple.domain.IReadDatabase;
import org.example.cqrssimple.domain.ItemCreatedEvent;
import org.example.cqrssimple.domain.readmodel.ItemListView;
import org.example.cqrssimple.domain.readmodel.SingleItemForList;

import java.util.SortedMap;
import java.util.TreeMap;

public class InMemoryFakeReadDatabase implements IReadDatabase {
    private final SortedMap<String, SingleItemForList> items = new TreeMap<>();

    @Override
    public ItemListView getItemList() {
        return new ItemListView(items.values());
    }

    @Override
    public void handle(ItemCreatedEvent itemCreatedEvent) {
        String itemId = itemCreatedEvent.getItemId();
        items.put(itemId, new SingleItemForList(itemId, itemCreatedEvent.getItemName()));
    }

    @Override
    public void deactivate(String itemId) {
        items.remove(itemId);
    }
}
