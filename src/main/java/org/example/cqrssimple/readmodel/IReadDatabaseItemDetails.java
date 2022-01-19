package org.example.cqrssimple.readmodel;

import java.util.Optional;

public interface IReadDatabaseItemDetails {
    void save(ItemDetailsDto itemDetailsDto);

    Optional<ItemDetailsDto> get(String itemId);

    void delete(String itemId);

    void rename(String itemId, String newName);

    void checkIn(String itemId, int quantity);
}
