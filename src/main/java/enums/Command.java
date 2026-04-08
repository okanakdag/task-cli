package enums;

public enum Command {
    ADD("add", 2, 2),
    UPDATE("update", 2, 2),
    DELETE("delete", 2, 2),
    LIST("list", 1, 2),
    MARK_IN_PROGRESS("mark-in-progress", 2, 2),
    MARK_DONE("mark-done", 2, 2),
    HELP("help", 1, 1);

    private final String description;
    private final int minArgs;
    private final int maxArgs;

    Command(String description, int minArgs, int maxArgs) {
        this.description = description;
        this.minArgs = minArgs;
        this.maxArgs = maxArgs;
    }

    public String getDescription() {
        return description;
    }

    public int getMinArgs() {
        return minArgs;
    }

    public int getMaxArgs() {
        return maxArgs;
    }

    public static Command fromString(String text) {
        for (Command command : Command.values()) {
            if (command.description.equalsIgnoreCase(text)) {
                return command;
            }
        }
        throw new IllegalArgumentException("Unknown command: " + text);
    }
}
