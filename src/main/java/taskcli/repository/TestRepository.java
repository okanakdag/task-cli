package taskcli.repository;
import java.util.ArrayList;

import taskcli.Task;
import taskcli.enums.Status;

// In memory repository for testing
public class TestRepository implements TaskRepository {

    private ArrayList<Task> tasks;

    public TestRepository() {
        tasks = new ArrayList<>();

        Task task1 = new Task(1, "Buy groceries");
        Task task2 = new Task(2, "Finish Java practice project");
        Task task3 = new Task(3, "Read about exceptions and testing");

        task2.updateStatus(Status.IN_PROGRESS);
        task3.updateStatus(Status.DONE);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
    }

    public ArrayList<Task> loadTaskList() {
        return new ArrayList<>(tasks);
    }

    public void saveTaskList(ArrayList<Task> taskList) {
        return;
    }
}
