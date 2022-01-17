package org.example.cqrssimple.command;

import org.example.cqrssimple.domain.IEventPublisher;
import org.example.cqrssimple.event.InMemoryEventStore;
import org.example.cqrssimple.event.ItemCreatedEvent;
import org.example.cqrssimple.domain.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class CommandBusShould {

    private CommandBus commandBus = new CommandBus();
    private InMemoryEventStore inMemoryEventStore;
    private IEventPublisher eventPublisher;

    @BeforeEach
    void setUp() {
        inMemoryEventStore = new InMemoryEventStore();
        eventPublisher = mock(IEventPublisher.class);
    }

    @Nested
    public class Common {
        private CommandHandler1 commandHandler1;
        private CommandHandler2 commandHandler2;
        private List<ICommandHandler> commandHandlers;

        @BeforeEach
        void setUp() {
            commandHandler1 = spy(new CommandHandler1());
            commandHandler2 = spy(new CommandHandler2());
            commandBus.registerHandler(commandHandler1);
            commandBus.registerHandler(commandHandler2);
        }

        @Test
        void callTheAppropriateHandler_whenSendCommand() {
            // GIVEN
            Command command1 = new Command1(UUID.randomUUID());
            Command command2 = new Command2(UUID.randomUUID());

            // WHEN send command 1
            commandBus.send(command1);

            // THEN only handler 1 is invoked
            verify(commandHandler1).handle(eq(command1));
            verify(commandHandler2, never()).handle(any());

            reset(commandHandler1, commandHandler2);

            // WHEN send command 2
            commandBus.send(command2);

            // THEN only handler 2 is invoked
            verify(commandHandler1, never()).handle(any());
            verify(commandHandler2).handle(eq(command2));
        }

        @Test
        void throwAnException_whenNoHandlerDefined() {
            // GIVEN
            CommandWithNoHandler commandWithNoHandler = new CommandWithNoHandler(UUID.randomUUID());

            // WHEN
            assertThatThrownBy(() -> commandBus.send(commandWithNoHandler)).isInstanceOf(NoHandlerAcceptsCommandException.class);
        }

        private static class Command1 extends Command {

            public Command1(UUID itemId) {
                super(itemId);
            }
        }

        private static class Command2 extends Command {

            public Command2(UUID itemId) {
                super(itemId);
            }
        }

        private static class CommandWithNoHandler extends Command {

            public CommandWithNoHandler(UUID itemId) {
                super(itemId);
            }
        }

        private static class CommandHandler1 implements ICommandHandler {

            @Override
            public void handle(Command command) {
                System.out.println();
            }

            @Override
            public boolean accept(Command command) {
                return command instanceof Command1;
            }
        }

        private static class CommandHandler2 implements ICommandHandler {

            @Override
            public void handle(Command command) {
                System.out.println();
            }

            @Override
            public boolean accept(Command command) {
                return command instanceof Command2;
            }
        }
    }

    @Nested
    public class CreateItem {

        private CreateItemCommandHandler createItemCommandHandler;
        private Repository repository;

        @BeforeEach
        void setUp() {
            repository = new Repository(inMemoryEventStore, eventPublisher);
            createItemCommandHandler = spy(new CreateItemCommandHandler(repository));
            commandBus.registerHandler(createItemCommandHandler);
        }

        @Test
        void saveAndPublishEvent_whenCreateItemCommandSent() {
            // GIVEN
            String itemName = "item name";
            UUID uuid = UUID.randomUUID();
            CreateItemCommand createItemCommand = new CreateItemCommand(uuid, itemName);
            ItemCreatedEvent expectedEvent = new ItemCreatedEvent(uuid, itemName);

            // WHEN
            commandBus.send(createItemCommand);

            // THEN
            assertThat(inMemoryEventStore.getEvents()).contains(expectedEvent);
            verify(eventPublisher).publish(eq(List.of(expectedEvent)));
        }
    }
}