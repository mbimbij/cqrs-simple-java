package org.example.cqrssimple;

public interface ICommandHandler<T extends Command> {
    void handle(T command);

    boolean accept(Command command);
}
