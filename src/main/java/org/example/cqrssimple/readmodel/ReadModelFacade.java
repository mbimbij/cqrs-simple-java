package org.example.cqrssimple.readmodel;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReadModelFacade {
    private final IReadDatabase readDatabase;

    public List<ItemListDto> getItemList() {
        return readDatabase.getItemList();
    }
}
