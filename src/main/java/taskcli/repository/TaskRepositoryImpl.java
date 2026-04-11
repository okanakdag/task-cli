package taskcli.repository;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.*;

import taskcli.Task;
import taskcli.exception.StorageException;

public class TaskRepositoryImpl implements TaskRepository{
    private TaskJsonMapper mapper;

    public TaskRepositoryImpl(TaskJsonMapper mapper) {
        this.mapper = mapper;
    }

    public ArrayList<Task> loadTaskList() {
        Path dataPath = Paths.get("data", "tasks.json");
        
        if (Files.notExists(dataPath)) {
            return new ArrayList<>();
        } 
        
        try {
            return mapper.parseJson(Files.readString(dataPath));
        } catch (IOException e) {
            throw new StorageException("Storage error", e);
        }
    }

    public void saveTaskList(ArrayList<Task> taskList) {
        try {
            Path dataPath = Paths.get("data", "tasks.json");
            Path tempPath = Paths.get("data", "tasks.tmp.json");
            Files.createDirectories(dataPath.getParent());
            Files.writeString(tempPath, mapper.serializeTaskList(taskList));
            Files.move(tempPath, dataPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Storage error", e);
        }
    }
}
