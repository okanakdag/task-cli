package taskcli.cli;

import java.time.format.DateTimeFormatter;
import java.util.List;
import taskcli.Task;

public final class TaskPrinter {
    private TaskPrinter() {}

    private static final DateTimeFormatter DATE_FORMAT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void printTaskList(List<Task> tasks) {
        tasks.forEach(task -> {
            System.out.println(formatTask(task));
            System.out.println("----------------------------------------");
    });
    }

    private static String formatTask(Task task) {
        return """
            Task #%d
            Description : %s
            Status      : %s
            Created At  : %s
            Updated At  : %s
            """.formatted(
            task.getId(),
            task.getDescription(),
            task.getStatus(),
            task.getCreatedAt().format(DATE_FORMAT),
            task.getUpdatedAt().format(DATE_FORMAT)
            ).stripTrailing();
    }

}
