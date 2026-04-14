package taskcli.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import taskcli.Task;
import taskcli.exception.StorageException;

public class TaskJsonMapperImpl implements TaskJsonMapper{

    @Override
    public String serializeTaskList(List<Task> tasks) {
        return tasks.stream()
            .map(this::taskToJson)
            .collect(Collectors.joining(",\n", "[\n", "\n]"));
    }

    @Override
    public ArrayList<Task> parseJson(String json) {
        ArrayList<Task> tasks = new ArrayList<>();

        json = json.trim();

        
        if (!json.startsWith("[") || !json.endsWith("]")) {
            throw new StorageException("Storage file contains Invalid JSON array", null);
        }

        String innerJson = json.substring(1, json.length() - 1).trim();

        if (innerJson.isEmpty()) {
            return tasks;
        }

        String[] jsonParts = innerJson.split("\\},\\s*\\{");

        for (String jsonPart : jsonParts) {
            String part = jsonPart.trim();
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
        return """
               {
                 "id": """ + task.getId() + ",\n" +
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
        int id = Integer.parseInt(extractJsonValue(json, "id"));
        String description = unescapeJson(extractJsonValue(json, "description"));
        String status = extractJsonValue(json, "status");
        LocalDateTime createdAt = LocalDateTime.parse(extractJsonValue(json, "createdAt"));
        LocalDateTime updatedAt = LocalDateTime.parse(extractJsonValue(json, "updatedAt"));
        return new Task(id, description, status, createdAt, updatedAt);
    }

    private String extractJsonValue(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(\"((?:\\\\.|[^\"])*)\"|([^,}\\s]+))");
        Matcher matcher = pattern.matcher(json);

        if (!matcher.find()) {
            throw new StorageException("Storage file can't be parsed", null);
        }

        String quotedValue = matcher.group(2);
        return quotedValue != null ? quotedValue : matcher.group(3);
    }

    private String unescapeJson(String text) {
        return text
            .replace("\\\"", "\"")
            .replace("\\\\", "\\");
    }
}