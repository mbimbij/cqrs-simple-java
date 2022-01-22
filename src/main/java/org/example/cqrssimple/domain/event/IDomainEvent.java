package org.example.cqrssimple.domain.event;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@ToString
@EqualsAndHashCode
public class IDomainEvent {
    String itemId;

    public IDomainEvent(String itemId) {
        this.itemId = itemId;
    }
}
