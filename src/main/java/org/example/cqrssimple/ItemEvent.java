package org.example.cqrssimple;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@EqualsAndHashCode
public class ItemEvent {
    String itemId;

    public ItemEvent(String itemId) {
        this.itemId = itemId;
    }
}
