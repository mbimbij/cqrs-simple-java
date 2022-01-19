package org.example.cqrssimple.command;

import org.example.cqrssimple.writemodel.InventoryItem;
import org.example.cqrssimple.writemodel.Repository;

public class RenameItemCommandHandler implements ICommandHandler<RenameItemCommand> {
    private final Repository repository;

    public RenameItemCommandHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(RenameItemCommand command) {
        InventoryItem item = repository.findById(command.getItemId());
        item.rename(command.getNewName());
        repository.save(item);
    }

    @Override
    public boolean accept(Command command) {
        return command instanceof RenameItemCommand;
    }
}
