package org.example.cqrssimple.domain.aggregate;

public abstract class ICommandHandler<T extends ICommand> {
    private final Class<T> acceptedEventType;

    protected ICommandHandler(Class<T> acceptedEventType) {
        this.acceptedEventType = acceptedEventType;
    }

    public abstract void handle(T command);

    public boolean accept(ICommand command) {
        return acceptedEventType.isAssignableFrom(command.getClass());
    }
}
