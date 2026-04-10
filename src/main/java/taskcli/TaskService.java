package taskcli;
import java.util.ArrayList;

import taskcli.repository.TaskRepository;

public class TaskService {
    private ArrayList<Task> taskList;
    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
        this.taskList = repository.loadTaskList();
    }

    public void addTask(String description) {
        repository.addTask(new Task(nextId(), description));
    }

    public void list() {
        taskList.forEach(System.out::println);
    }

    public void list(String status) {

    }

    public void updateDescription(String id, String description) {
    }

    public void deleteTask(String id) {
    }

    public void markInProgress(String id) {
    }

    public void markDone(String id) {
    }

    private int nextId() {
        return taskList.stream()
            .mapToInt(Task::getId)
            .max()
            .orElse(0) + 1;
    }
}
