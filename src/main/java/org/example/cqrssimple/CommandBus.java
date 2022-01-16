package org.example.cqrssimple;

import java.util.List;
import java.util.stream.Collectors;

public class CommandBus {
    List<ICommandHandler> commandHandlers;

    public CommandBus(List<ICommandHandler> commandHandlers) {
        this.commandHandlers = commandHandlers;
    }

    public void send(Command command) {
        List<ICommandHandler> commandHandlers = this.commandHandlers.stream()
                                                                    .filter(iCommandHandler -> iCommandHandler.accept(command))
                                                                    .collect(Collectors.toList());
        if (commandHandlers.isEmpty()) {
            throw new NoHandlerAcceptsCommandException(command);
        }

        commandHandlers.forEach(iCommandHandler -> iCommandHandler.handle(command));
    }
}
