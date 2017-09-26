package start.api.impl;

import start.api.UserTaskAPI;
import start.api.data.Task;
import start.api.data.UserTask;

import java.util.List;

class InMemoryUserTaskAPI implements UserTaskAPI {
  @Override
  public Task getTask(int userId, int taskId) {
    return null;
  }

  @Override
  public List<Task> getTasks(int userId) {
    return null;
  }

  @Override
  public List<Task> getTasksWithTags(int userId, List<UserTask.Tag> tags) {
    return null;
  }

  @Override
  public List<Task> getTasksWithState(int userId, Task.TaskState taskState) {
//      return userTasksMap.get(userId).stream().filter(task -> task.getState().equals(taskState)).collect(Collectors.toList());
    return null;
  }

  @Override
  public Task addTask(int userId, int taskId, List<UserTask.Tag> tags) {
    return null;
  }

  @Override
  public Task updateTask(int userId, int taskId, List<UserTask.Tag> tags) {
    return null;
  }

  @Override
  public Task deleteTask(int userId, int taskId) {
    return null;
  }
}