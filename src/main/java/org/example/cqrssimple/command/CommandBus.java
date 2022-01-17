package org.example.cqrssimple.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandBus {
    List<ICommandHandler<? super Command>> commandHandlers;

    public CommandBus() {
        commandHandlers = new ArrayList<>();
    }

    public CommandBus(List<ICommandHandler<? super Command>> commandHandlers) {
        this.commandHandlers = commandHandlers;
    }

    public void registerHandler(ICommandHandler commandHandler) {
        commandHandlers.add(commandHandler);
    }

    public <T extends Command> void send(T command) {
        List<ICommandHandler<? super Command>> commandHandlers = this.commandHandlers.stream()
                                                                                     .filter(iCommandHandler -> iCommandHandler.accept(command))
                                                                                     .collect(Collectors.toList());
        if (commandHandlers.isEmpty()) {
            throw new NoHandlerAcceptsCommandException(command);
        }

        commandHandlers.forEach(iCommandHandler -> iCommandHandler.handle(command));
    }
}
