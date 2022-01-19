package org.example.cqrssimple.command;

import org.example.cqrssimple.domain.InventoryItem;
import org.example.cqrssimple.domain.Repository;

public class RemoveItemCommandHandler implements ICommandHandler<RemoveItemCommand> {
    private Repository repository;

    public RemoveItemCommandHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(RemoveItemCommand command) {
        InventoryItem item = repository.findById(command.getItemId());
        item.remove(command.getQuantity());
        repository.save(item);
    }

    @Override
    public boolean accept(Command command) {
        return command instanceof RemoveItemCommand;
    }
}
