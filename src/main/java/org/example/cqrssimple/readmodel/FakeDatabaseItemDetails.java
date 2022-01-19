package org.example.cqrssimple.readmodel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeDatabaseItemDetails implements IReadDatabaseItemDetails {
    private Map<String, ItemDetailsDto> items = new HashMap<>();

    @Override
    public void save(ItemDetailsDto itemDetailsDto) {
        items.put(itemDetailsDto.getUuid(), itemDetailsDto);
    }

    @Override
    public Optional<ItemDetailsDto> get(String itemId) {
        return Optional.ofNullable(items.get(itemId));
    }

    @Override
    public void delete(String itemId) {

    }

    @Override
    public void rename(String itemId, String newName) {

    }
}
