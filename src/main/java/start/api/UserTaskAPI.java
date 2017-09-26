package start.api;

import start.api.data.Task;
import start.api.data.UserTask;

import java.util.List;

public interface UserTaskAPI {
  Task getTask(int userId, int taskId);

  List<Task> getTasks(int userId);

  List<Task> getTasksWithTags(int userId, List<UserTask.Tag> tags);

  List<Task> getTasksWithState(int userId, Task.TaskState taskState);

  Task addTask(int userId, int taskId, List<UserTask.Tag> tags);

  Task updateTask(int userId, int taskId, List<UserTask.Tag> tags);

  Task deleteTask(int userId, int taskId);
}
