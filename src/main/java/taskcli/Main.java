package taskcli;

import taskcli.exception.StorageException;
import taskcli.exception.TaskNotFoundException;
import taskcli.repository.TaskRepository;
import taskcli.repository.TaskRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        try {
            TaskRepository taskRepository = new TaskRepositoryImpl();
            TaskService taskService = new TaskService(taskRepository);
            TaskCli taskCli = new TaskCli(taskService, args);
            taskCli.run();
        } catch(IllegalArgumentException | StorageException | TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
