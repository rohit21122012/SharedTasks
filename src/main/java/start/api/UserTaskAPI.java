package start.api;

import start.model.Task;
import start.model.UserTask;

import java.util.Set;

public interface UserTaskAPI {
  Task getTask(int userId, int taskId);

  Set<Task> getTasks(int userId);

  Set<Task> getTasksWithTags(int userId, Set<UserTask.Tag> tags);

  Set<Task> getTasksWithState(int userId, Task.TaskState taskState);

  Task addTask(int userId, int taskId, Set<UserTask.Tag> tags);

  Set<UserTask.Tag> updateTaskTags(int userId, int taskId, Set<UserTask.Tag> tags);

  Task deleteTask(int userId, int taskId);
}
