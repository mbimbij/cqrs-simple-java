package org.example.cqrssimple.domain.aggregate;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class CreateItemCommand extends ICommand {
    String itemName;

    public CreateItemCommand(String itemId, String itemName) {
        super(itemId);
        this.itemName = itemName;
    }
}
