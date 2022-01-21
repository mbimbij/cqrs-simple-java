package org.example.cqrssimple;

public class ItemDeletedEvent extends ItemEvent {
    public ItemDeletedEvent(String itemId) {
        super(itemId);
    }
}
