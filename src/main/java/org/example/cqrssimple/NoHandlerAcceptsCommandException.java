package org.example.cqrssimple;

public class NoHandlerAcceptsCommandException extends RuntimeException {
    private Command unhandledCommand;

    public NoHandlerAcceptsCommandException(Command command) {
        super(command.getClass().getName());
        this.unhandledCommand = command;
    }
}
