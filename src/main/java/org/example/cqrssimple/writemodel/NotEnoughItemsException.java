package org.example.cqrssimple.writemodel;

import java.util.UUID;

public class NotEnoughItemsException extends RuntimeException {
    public NotEnoughItemsException(UUID uuid, int itemQuantity, int quantityToRemove) {
        super("%s: not enough items (%d) to remove %d".formatted(uuid.toString(), itemQuantity, quantityToRemove));
    }
}
