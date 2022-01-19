package org.example.cqrssimple.command;

import org.example.cqrssimple.domain.InventoryItem;
import org.example.cqrssimple.domain.Repository;

public class CheckInItemCommandHandler implements ICommandHandler<CheckInItemCommand> {
    private Repository repository;

    public CheckInItemCommandHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    public boolean accept(Command command) {
        return command instanceof CheckInItemCommand;
    }

    @Override
    public void handle(CheckInItemCommand command) {
        InventoryItem item = repository.findById(command.getItemId());
        item.checkIn(command.getQuantity());
        repository.save(item);
    }
}
