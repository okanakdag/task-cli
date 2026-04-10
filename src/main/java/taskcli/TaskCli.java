package taskcli;
import taskcli.enums.Command;
import taskcli.repository.TestRepository;

public class TaskCli {

    public static void main(String[] args) {
        try {
            parseCommand(args);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void parseCommand(String[] args) {
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

        TaskService taskService = new TaskService(new TestRepository());

        switch (command) {
            case ADD:
                taskService.addTask(args[1]);
                break;
            case UPDATE:
                taskService.updateDescription(args[1], args[2]);
                break;
            case DELETE:
                taskService.deleteTask(args[1]);
                break;
            case LIST:
                if ((args.length == 2)) {
                    taskService.list(args[1]);
                } else {
                    taskService.list();
                }
                break;
            case MARK_IN_PROGRESS:
                taskService.markInProgress(args[1]);
                break;
            case MARK_DONE:
                taskService.markDone(args[1]);
                break;
            case HELP:
                help();
                break;
        }
    }

    private static void help() {
        System.out.println("""
            Usage:
              task-cli <command> [arguments]
            
            Commands:
              add <description>           Add a new task
              update <id> <description>   Update a task
              delete <id>                 Delete a task
              list [status]               List all tasks or filter by status
              mark-in-progress <id>       Mark a task as in progress
              mark-done <id>              Mark a task as done
              help                        Show this help message
            
            Examples:
              task-cli add "Buy groceries"
              task-cli update 1 "Buy milk and eggs"
              task-cli delete 1
              task-cli list
              task-cli list done
              task-cli mark-in-progress 2
              task-cli mark-done 2
            """);
    }
}
