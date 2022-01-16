package org.example.cqrssimple;

public class NoHandlerAcceptsCommandException extends RuntimeException {
    private Command unhandledCommand;

    public NoHandlerAcceptsCommandException(Command unhandledCommand) {
        super(unhandledCommand.getClass().getName());
        this.unhandledCommand = unhandledCommand;
    }
}
