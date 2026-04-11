package taskcli.repository;

import taskcli.Task;

public interface TaskJsonMapper {
    public String toJson(Task task);

    public Task toTask(String json);
}