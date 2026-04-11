package taskcli.repository;

import java.util.List;
import java.util.ArrayList;
import taskcli.Task;

public interface TaskJsonMapper {
    public String serializeTaskList(List<Task> task);

    public ArrayList<Task> parseJson(String json);
}