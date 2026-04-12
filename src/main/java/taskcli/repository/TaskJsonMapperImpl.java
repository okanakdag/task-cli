package taskcli.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import taskcli.Task;
import taskcli.exception.StorageException;

public class TaskJsonMapperImpl implements TaskJsonMapper{
    public String serializeTaskList(List<Task> tasks) {
        return tasks.stream()
            .map(this::taskToJson)
            .collect(Collectors.joining(",\n", "[\n", "\n]"));
    }

    public ArrayList<Task> parseJson(String json) {
        ArrayList<Task> tasks = new ArrayList<>();

        json = json.trim();

        
        if (!json.startsWith("[") || !json.endsWith("]")) {
            throw new StorageException("Invalid JSON array", null);
        }

        String innerJson = json.substring(1, json.length() - 1).trim();

        if (innerJson.isEmpty()) {
            return tasks;
        }

        String[] jsonParts = innerJson.split("\\},\\s*\\{");

        for (int i = 0; i < jsonParts.length; i++) {
            String part = jsonParts[i].trim();

            // split function removes braces, so it's added back here
            if (!part.startsWith("{")) {
                part = "{" + part;
            }
            if (!part.endsWith("}")) {
                part = part + "}";
            }

            tasks.add(jsonToTask(part));
        }
        return tasks;
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

    private Task jsonToTask(String json) {

    }
}
