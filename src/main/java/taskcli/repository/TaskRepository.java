package taskcli.repository;

import java.util.ArrayList;

import taskcli.Task;

public interface TaskRepository {

    ArrayList<Task> loadTaskList();

    void saveTaskList(ArrayList<Task> taskList);
}
