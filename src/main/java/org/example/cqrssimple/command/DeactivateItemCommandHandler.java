package org.example.cqrssimple.command;

import org.example.cqrssimple.domain.InventoryItem;
import org.example.cqrssimple.domain.Repository;

public class DeactivateItemCommandHandler implements ICommandHandler<DeactivateItemCommand> {
    private final Repository repository;

    public DeactivateItemCommandHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(DeactivateItemCommand command) {
        InventoryItem item = repository.findById(command.getItemId());
        item.deactivate();
        repository.save(item);
    }

    @Override
    public boolean accept(Command command) {
        return command instanceof DeactivateItemCommand;
    }
}
