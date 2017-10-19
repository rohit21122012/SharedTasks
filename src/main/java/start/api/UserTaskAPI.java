package start.api;

import start.model.Tag;
import start.model.Task;

import java.util.Set;

public interface UserTaskAPI {
  Task getTask(int userId, int taskId);

  Set<Task> getTasks(int userId);

  Set<Task> getTasksWithTags(int userId, Set<Tag> tags);

  Set<Task> getTasksWithState(int userId, Task.TaskState taskState);

  Task addTask(int userId, int taskId, Set<Tag> tags);

  Set<Tag> updateTaskTags(int userId, int taskId, Set<Tag> tags);

  Task deleteTask(int userId, int taskId);
}
