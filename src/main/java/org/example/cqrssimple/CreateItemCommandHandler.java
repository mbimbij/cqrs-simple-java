package org.example.cqrssimple;

import lombok.RequiredArgsConstructor;

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
