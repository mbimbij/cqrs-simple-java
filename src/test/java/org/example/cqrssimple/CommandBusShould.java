package org.example.cqrssimple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class CommandBusShould {

    private CommandBus commandBus;

    @Nested
    public class Common{
        private CommandHandler1 commandHandler1;
        private CommandHandler2 commandHandler2;
        private List<ICommandHandler> commandHandlers;

        @BeforeEach
        void setUp() {
            commandHandler1 = spy(new CommandHandler1());
            commandHandler2 = spy(new CommandHandler2());
            commandHandlers = List.of(commandHandler1, commandHandler2);

            commandBus = new CommandBus(commandHandlers);
        }

        @Test
        void callTheAppropriateHandler_whenSendCommand() {
            // GIVEN
            Command command1 = new Command1();
            Command command2 = new Command2();

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
            CommandWithNoHandler commandWithNoHandler = new CommandWithNoHandler();

            // WHEN
            assertThatThrownBy(() -> commandBus.send(commandWithNoHandler)).isInstanceOf(NoHandlerAcceptsCommandException.class);
        }

        private static class Command1 extends Command {

        }

        private static class Command2 extends Command {

        }

        private static class CommandWithNoHandler extends Command {

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
}
