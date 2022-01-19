package org.example.cqrssimple.readmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FakeReadDatabase implements IReadDatabase {
    private final List<ItemListDto> itemListDtos = new ArrayList<>();

    @Override
    public Collection<ItemListDto> getItemList() {
        return Collections.unmodifiableList(itemListDtos);
    }

    @Override
    public void save(ItemListDto itemListDto) {
        itemListDtos.add(itemListDto);
    }
}
