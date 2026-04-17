package taskcli;
import taskcli.cli.CommandParser;
import taskcli.cli.TaskPrinter;
import taskcli.enums.Command;
import taskcli.enums.Status;

public class TaskCli {

    private  final TaskService taskService;
    private final String[] args;

    public TaskCli(TaskService taskService, String[] args) {
        this.taskService = taskService;
        this.args = args;
    }

    public void run() {
        Command command = CommandParser.parse(args);
        switch (command) {
            case ADD -> taskService.addTask(args[1]);
            case UPDATE -> taskService.updateDescription(convertId(args[1]), args[2]);
            case DELETE -> taskService.deleteTask(convertId(args[1]));
            case LIST -> callList();
            case MARK_IN_PROGRESS -> taskService.markInProgress(convertId(args[1]));
            case MARK_DONE -> taskService.markDone(convertId(args[1]));
            case HELP -> System.out.println(HELP_TEXT);
        }
    }

    private void callList() {
        if ((args.length == 2)) {
            TaskPrinter.printTaskList(taskService.list(Status.fromString(args[1])));
        } else {
            TaskPrinter.printTaskList(taskService.list());
        }
    }

    private static final String HELP_TEXT = """
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
            """;

    private static int convertId(String idString) {
        try {
            return Integer.parseInt(idString);
        } catch (NumberFormatException _) {
            throw new IllegalArgumentException("Invalid task id: " + idString);
        }
    }
}
