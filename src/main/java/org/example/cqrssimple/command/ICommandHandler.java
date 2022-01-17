package org.example.cqrssimple.command;

public interface ICommandHandler<T extends Command> {
    void handle(T command);

    boolean accept(Command command);
}
