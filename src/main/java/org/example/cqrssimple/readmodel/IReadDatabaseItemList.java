package org.example.cqrssimple.readmodel;

import java.util.Collection;

public interface IReadDatabaseItemList {
    Collection<ItemListDto> getItemList();
    void save(ItemListDto itemListDto);

    void delete(String itemId);

    void rename(String toString, String newName);
}
