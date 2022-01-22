package org.example.cqrssimple.domain.aggregate;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Value
public class CheckInItemCommand extends ICommand {
    int quantity;

    public CheckInItemCommand(String itemId, int quantity) {
        super(itemId);
        this.quantity = quantity;
    }
}
