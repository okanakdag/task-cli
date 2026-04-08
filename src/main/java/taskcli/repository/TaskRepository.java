package taskcli.repository;

import java.util.ArrayList;

import taskcli.Task;

public interface TaskRepository {

    void addTask(Task task);

    void deleteTaskById(int id);

    ArrayList<Task> loadTaskList();
}
