package server.handler;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import core.command.Command;
import core.handler.ShellHandler;
import server.runner.RecursionFoundException;
import server.runner.Runner;

/**
 * Class providing user interactive shell.
 *
 * @author ivatolm
 */
public class ServerShellHandler extends ShellHandler {

    /** User program runner */
    private Runner runner;

    /**
     * Constructs new {@code Shell} with provided arguments.
     */
    public ServerShellHandler(Runner runner) {
        super();
        this.runner = runner;
    }

    /**
     * Runs interactive shell until EOF.
     * Work cycle:
     * 1. get user input
     * 2. send command to the pipe
     */
    @Override
    public void _run() {
        try {
            LinkedList<Command> commands = this.parseCommands(null);

            byte[] data = new byte[] { 1 };
            ByteBuffer buffer = ByteBuffer.wrap(data);
            this.syncWait(buffer);

            try {
                this.runner.addSubroutine(commands);
            } catch (RecursionFoundException e) {
                System.err.println("Recursion detected. Skipping...");
                return;
            }

            this.runner.run();

            this.syncNotify();
        } catch (NoSuchElementException e) {
            System.out.println("\nExiting by Ctrl-D (EOF)");
            this.running = false;
        }
    }

}
