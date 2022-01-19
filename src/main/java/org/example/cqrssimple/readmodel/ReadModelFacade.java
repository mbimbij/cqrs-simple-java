package org.example.cqrssimple.readmodel;

import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class ReadModelFacade {
    private final IReadDatabase readDatabase;

    public Collection<ItemListDto> getItemList() {
        return readDatabase.getItemList();
    }
}
