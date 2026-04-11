package taskcli.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.*;

import taskcli.Task;
import taskcli.exception.StorageException;

public class TaskRepositoryImpl implements TaskRepository{
    private TaskJsonMapper taskJsonmapper;

    public TaskRepositoryImpl(TaskJsonMapper mapper) {
        this.taskJsonmapper = mapper;
    }

    public ArrayList<Task> loadTaskList() {
        Path dataPath = Paths.get("data", "tasks.json");
        ArrayList<Task> tasks = new ArrayList<>();

        if (Files.notExists(dataPath)) {
            return tasks;
        }

        //TODO
        return tasks;
    }

    public void saveTaskList(ArrayList<Task> taskList) {
        try {
            Path dataPath = Paths.get("data", "tasks.json");
            Path tempPath = Paths.get("data", "tasks.tmp.json");
            Files.createDirectories(dataPath.getParent());
            Files.writeString(tempPath, taskJsonmapper.serializeTaskList(taskList));
            Files.move(tempPath, dataPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Storage error", e);
        }
    }
}
