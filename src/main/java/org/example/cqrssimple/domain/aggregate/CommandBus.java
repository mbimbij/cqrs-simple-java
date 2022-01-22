package org.example.cqrssimple.domain.aggregate;

import java.util.ArrayList;
import java.util.List;

public class CommandBus {
    private final List<ICommandHandler> commandHandlers = new ArrayList<>();

    public void send(ICommand command) {
        commandHandlers.stream()
                       .filter(commandHandler -> commandHandler.accept(command))
                       .forEach(commandHandler -> commandHandler.handle(command));
    }

    public void register(ICommandHandler<?> commandHandler) {
        commandHandlers.add(commandHandler);
    }
}
