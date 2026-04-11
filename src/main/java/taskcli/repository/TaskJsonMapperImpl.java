package taskcli.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import taskcli.Task;

public class TaskJsonMapperImpl implements TaskJsonMapper{
    public String serializeTaskList(List<Task> tasks) {
        return tasks.stream()
            .map(this::taskToJson)
            .collect(Collectors.joining(",\n", "[\n", "\n]"));
    }

    public ArrayList<Task> parseJson(String json) {
        return null;
    }

    private String taskToJson(Task task) {
        return "{\n" +
            "  \"id\": " + task.getId() + ",\n" +
            "  \"description\": \"" + escapeJson(task.getDescription()) + "\",\n" +
            "  \"status\": \"" + task.getStatus().name() + "\",\n" +
            "  \"createdAt\": \"" + task.getCreatedAt() + "\",\n" +
            "  \"updatedAt\": \"" + task.getUpdatedAt() + "\"\n" +
            "}";
    }

    private String escapeJson(String text) {
        return text
            .replace("\\", "\\\\")
            .replace("\"", "\\\"");
    }
}
