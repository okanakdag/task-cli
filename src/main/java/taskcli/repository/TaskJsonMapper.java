package taskcli.repository;

import java.util.List;
import taskcli.Task;

public interface TaskJsonMapper {
    public String serializeTaskList(List<Task> task);

    public List<Task> parseJson(String json);
}