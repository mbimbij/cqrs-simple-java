package org.example.cqrssimple;

import java.util.List;

public class CommandSender {
    List<ICommandHandler> commandHandlers;

    public CommandSender(List<ICommandHandler> commandHandlers) {
        this.commandHandlers = commandHandlers;
    }

    public void send(Command command) {
        commandHandlers.stream()
                       .filter(iCommandHandler -> iCommandHandler.accept(command))
                       .forEach(iCommandHandler -> iCommandHandler.handle(command));
    }
}
