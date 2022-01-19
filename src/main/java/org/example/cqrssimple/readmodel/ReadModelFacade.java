package org.example.cqrssimple.readmodel;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ReadModelFacade {
    private final IReadDatabaseItemList readDatabaseItemList;
    private final IReadDatabaseItemDetails readDatabaseItemDetails;

    public Collection<ItemListDto> getItemList() {
        return readDatabaseItemList.getItemList();
    }

    public Optional<ItemDetailsDto> getItemDetails(UUID uuid) {
        return readDatabaseItemDetails.get(uuid.toString());
    }
}
