package org.example.cqrssimple.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@EqualsAndHashCode
public class IDomainEvent {
    String itemId;

    public IDomainEvent(String itemId) {
        this.itemId = itemId;
    }
}
