package taskcli;
import java.util.List;
import taskcli.enums.Command;
import taskcli.enums.Status;
import taskcli.exception.StorageException;
import taskcli.exception.TaskNotFoundException;
import taskcli.repository.TaskRepositoryImpl;

public class TaskCli {

    public static void main(String[] args) {
        try {
            parseCommand(args);
        } catch(IllegalArgumentException | StorageException | TaskNotFoundException e) {
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

        TaskService taskService = new TaskService(new TaskRepositoryImpl());

        switch (command) {
            case ADD -> taskService.addTask(args[1]);
            case UPDATE -> taskService.updateDescription(convertId(args[1]), args[2]);
            case DELETE -> taskService.deleteTask(convertId(args[1]));
            case LIST -> callList(args, taskService);
            case MARK_IN_PROGRESS -> taskService.markInProgress(convertId(args[1]));
            case MARK_DONE -> taskService.markDone(convertId(args[1]));
            case HELP -> help();
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
              task-cli list in-progress
              task-cli mark-in-progress 2
              task-cli mark-done 2
            """);
    }

    private static void callList(String[] args, TaskService taskService) {
        if ((args.length == 2)) {
            printTaskList(taskService.list(Status.fromString(args[1])));
        } else {
            printTaskList(taskService.list());
        }

    }

    private static int convertId(String idString) {
        try {
            return Integer.parseInt(idString);
        } catch (NumberFormatException _) {
            throw new IllegalArgumentException("Invalid task id: " + idString);
        }
    }

    private static void printTaskList(List<Task> tasks) {
        tasks.forEach(task -> System.out.println(formatTask(task) + "\n"));
    }

    private static String formatTask(Task task) {
        return "Task id: " + task.getId() +
                "\nDescription: " + task.getDescription() +
                "\nStatus: " + task.getStatus() +
                "\nCreated at " + task.getCreatedAt() +
                "\nLast updated at " + task.getUpdatedAt();
    }
}
