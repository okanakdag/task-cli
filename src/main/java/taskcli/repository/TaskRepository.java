package taskcli.repository;

import java.util.List;
import taskcli.Task;

public interface TaskRepository {

    List<Task> loadTaskList();

    void saveTaskList(List<Task> taskList);
}
