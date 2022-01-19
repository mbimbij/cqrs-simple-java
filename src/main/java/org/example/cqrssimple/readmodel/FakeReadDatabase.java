package org.example.cqrssimple.readmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeReadDatabase implements IReadDatabase {
    private final List<ItemListDto> itemListDtos = new ArrayList<>();

    @Override
    public List<ItemListDto> getItemList() {
        return itemListDtos;
    }
}
