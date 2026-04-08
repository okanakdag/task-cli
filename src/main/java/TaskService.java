import java.util.ArrayList;

import repository.TaskRepository;

public class TaskService {
    private ArrayList<Task> taskList;
    private TaskRepository storage;

    public TaskService() {
        this.taskList = new ArrayList<Task>();
        this.storage = new TaskRepository();
        this.loadTaskList();
    }

    private void loadTaskList() {

    }

    // Commands
    public void addTask(String description) {

    }

    public void list() {

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
}
