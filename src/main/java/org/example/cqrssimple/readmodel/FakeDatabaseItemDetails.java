package org.example.cqrssimple.readmodel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeDatabaseItemDetails implements IReadDatabaseItemDetails {
    private final Map<String, ItemDetailsDto> items = new HashMap<>();

    @Override
    public void save(ItemDetailsDto itemDetailsDto) {
        items.put(itemDetailsDto.getUuid(), itemDetailsDto);
    }

    @Override
    public Optional<ItemDetailsDto> get(String itemId) {
        return Optional.ofNullable(items.get(itemId));
    }

    @Override
    public void deactivate(String itemId) {
        items.remove(itemId);
    }

    @Override
    public void rename(String itemId, String newName) {
        ItemDetailsDto itemDetailsDto = items.get(itemId);
        itemDetailsDto.setName(newName);
    }

    @Override
    public void checkIn(String itemId, int checkInQuantity) {
        ItemDetailsDto itemDetailsDto = items.get(itemId);
        itemDetailsDto.setQuantity(itemDetailsDto.getQuantity() + checkInQuantity);
    }

    @Override
    public void remove(String itemId, int removeQuantity) {
        ItemDetailsDto itemDetailsDto = items.get(itemId);
        itemDetailsDto.setQuantity(itemDetailsDto.getQuantity() - removeQuantity);
    }
}
