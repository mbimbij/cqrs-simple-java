package org.example.cqrssimple;

public interface ICommandHandler {
    void handle(Command command);

    boolean accept(Command command);
}
