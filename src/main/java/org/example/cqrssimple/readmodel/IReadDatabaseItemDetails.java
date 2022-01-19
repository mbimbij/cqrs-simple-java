package org.example.cqrssimple.readmodel;

import java.util.Optional;

public interface IReadDatabaseItemDetails {
    void save(ItemListDto itemListDto);

    Optional<ItemDetailsDto> get(String itemId);

    void delete(String itemId);

    void rename(String itemId, String newName);
}
