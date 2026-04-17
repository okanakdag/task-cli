package taskcli.cli;

import taskcli.enums.Command;

public final class CommandParser {
    private CommandParser() {}

    public static Command parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Missing command. Use: task-cli help");
        }
        if (args.length > 3) {
            throw new IllegalArgumentException("Too many arguments. Use: task-cli help");
        }

        Command command = Command.fromString(args[0]);

        if (command.getMinArgs() > args.length) {
            throw new IllegalArgumentException("Missing arguments for command: " + args[0]);
        }
        if (command.getMaxArgs() < args.length) {
            throw new IllegalArgumentException("Too many arguments for command: " + args[0]);
        }

        return command;
    }
}
