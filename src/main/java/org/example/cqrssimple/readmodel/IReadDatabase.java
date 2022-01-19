package org.example.cqrssimple.readmodel;

import java.util.Collection;

public interface IReadDatabase {
    Collection<ItemListDto> getItemList();
    void save(ItemListDto itemListDto);
}
