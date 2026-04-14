package taskcli;
import java.util.ArrayList;
import taskcli.enums.Status;
import taskcli.repository.TaskRepository;

public class TaskService {
    private final ArrayList<Task> taskList;
    private final TaskRepository repository;

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

    public void updateDescription(int id, String description) {
        taskById(id).updateDescription(description);
        repository.saveTaskList(taskList);
    }

    public void deleteTask(int id) {
        taskList.remove(taskById(id));
        repository.saveTaskList(taskList);
    }

    public void markInProgress(int id) {
        taskById(id).updateStatus(Status.IN_PROGRESS);
        repository.saveTaskList(taskList);
    }

    public void markDone(int id) {
        taskById(id).updateStatus(Status.DONE);
        repository.saveTaskList(taskList);
    }

    private int nextId() {
        return taskList.stream()
            .mapToInt(Task::getId)
            .max()
            .orElse(0) + 1;
    }

    private Task taskById(int id) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                return task;
            }
        }

        throw new IllegalArgumentException("Task not found: " + id);
    }
}
