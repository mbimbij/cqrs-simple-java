package org.example.cqrssimple.readmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    @Override
    public void deleteById(String itemId) {
        itemListDtos.removeIf(itemListDto -> Objects.equals(itemId, itemListDto.getUuid()));
    }

    @Override
    public void rename(String itemId, String newName) {
        deleteById(itemId);
        save(new ItemListDto(itemId, newName));
    }
}
