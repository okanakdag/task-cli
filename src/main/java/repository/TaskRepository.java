package repository;

import java.util.ArrayList;

public interface TaskRepository {

    void addTask();

    void deleteTaskById();

    ArrayList<Task> loadTaskList();
}
