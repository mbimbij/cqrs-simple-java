package org.example.cqrssimple.domain.readmodel;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
public class ItemListView {
    private final List<SingleItemForList> items;

    public ItemListView(Collection<SingleItemForList> items) {
        this.items = new ArrayList<>(items);
    }

    public List<SingleItemForList> getItems() {
        return Collections.unmodifiableList(items);
    }
}
