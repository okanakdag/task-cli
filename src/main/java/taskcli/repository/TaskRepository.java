package taskcli.repository;

import java.util.ArrayList;

import taskcli.Task;

public interface TaskRepository {

    void addTask();

    void deleteTaskById();

    ArrayList<Task> loadTaskList();
}
