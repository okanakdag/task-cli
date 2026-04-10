package taskcli;
import java.util.ArrayList;

import taskcli.repository.TaskRepository;
import taskcli.enums.Status;

public class TaskService {
    private ArrayList<Task> taskList;
    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
        this.taskList = repository.loadTaskList();
    }

    public void addTask(String description) {
        taskList.add(new Task(nextId(), description));
        repository.saveTaskList(taskList);
    }

    public void list() {
        taskList.forEach(System.out::println);
    }

    public void list(Status status) {
        taskList.stream()
            .filter(task -> task.getStatus() == status)
            .forEach(System.out::println);
    }

    public void updateDescription(String id, String description) {
        taskById(id).updateDescription(description);
        repository.saveTaskList(taskList);
    }

    public void deleteTask(String id) {
        taskList.remove(taskById(id));
        repository.saveTaskList(taskList);
    }

    public void markInProgress(String id) {
        taskById(id).updateStatus(Status.IN_PROGRESS);
        repository.saveTaskList(taskList);
    }

    public void markDone(String id) {
        taskById(id).updateStatus(Status.DONE);
        repository.saveTaskList(taskList);
    }

    private int nextId() {
        return taskList.stream()
            .mapToInt(Task::getId)
            .max()
            .orElse(0) + 1;
    }

    private int convertId(String idString) {
        try {
            return Integer.valueOf(idString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task id: " + idString);
        }
    }

    private Task taskById(String idString) {
        int id = convertId(idString);
        for (Task task : taskList) {
            if (task.getId() == id) {
                return task;
            }
        }

        throw new IllegalArgumentException("Task not found: " + id);
    }
}
