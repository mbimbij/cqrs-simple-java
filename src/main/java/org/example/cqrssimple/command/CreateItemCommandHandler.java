package org.example.cqrssimple.command;

import lombok.RequiredArgsConstructor;
import org.example.cqrssimple.writemodel.InventoryItem;
import org.example.cqrssimple.writemodel.Repository;

@RequiredArgsConstructor
public class CreateItemCommandHandler implements ICommandHandler<CreateItemCommand> {
    private final Repository repository;

    @Override
    public void handle(CreateItemCommand command) {
        InventoryItem inventoryItem = new InventoryItem(command.getItemId(), command.getItemName());
        repository.save(inventoryItem);
    }

    @Override
    public boolean accept(Command command) {
        return command instanceof CreateItemCommand;
    }
}
