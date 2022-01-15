package org.example.cqrssimple;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class CommandSenderShould {
    @Test
    void callTheAppropriateHandler_whenSendCommand() {
        // GIVEN
        CommandHandler1 commandHandler1 = spy(new CommandHandler1());
        CommandHandler2 commandHandler2 = spy(new CommandHandler2());
        List<ICommandHandler> commandHandlers = List.of(commandHandler1, commandHandler2);
        CommandSender commandSender = new CommandSender(commandHandlers);

        Command command1 = new Command1();
        Command command2 = new Command2();

        // WHEN send command 1
        commandSender.send(command1);

        // THEN only handler 1 is invoked
        verify(commandHandler1).handle(eq(command1));
        verify(commandHandler2, never()).handle(any());

        reset(commandHandler1, commandHandler2);

        // WHEN send command 2
        commandSender.send(command2);

        // THEN only handler 2 is invoked
        verify(commandHandler1, never()).handle(any());
        verify(commandHandler2).handle(eq(command2));
    }

    private static class Command1 extends Command {

    }

    private static class Command2 extends Command {

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
