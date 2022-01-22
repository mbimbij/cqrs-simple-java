package org.example.cqrssimple.domain.aggregate;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@EqualsAndHashCode
public class ICommand {
    String itemId;

    public ICommand(String itemId) {
        this.itemId = itemId;
    }
}
