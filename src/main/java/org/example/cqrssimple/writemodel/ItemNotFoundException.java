package org.example.cqrssimple.writemodel;

import java.util.UUID;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(UUID itemId) {
        super(itemId.toString());
    }
}
