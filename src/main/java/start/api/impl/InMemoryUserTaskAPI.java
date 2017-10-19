package start.api.impl;

import start.api.UserTaskAPI;
import start.data.Task;
import start.data.UserTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class InMemoryUserTaskAPI implements UserTaskAPI {

  private final InMemoryAPI inMemoryAPI;
  private Map<Integer, Map<Integer, Set<UserTask.Tag>>> userTasksMap = new ConcurrentHashMap<>();

  InMemoryUserTaskAPI(InMemoryAPI inMemoryAPI) {
    this.inMemoryAPI = inMemoryAPI;
  }

  @Override
  public Task getTask(int userId, int taskId) {
    if (userTasksMap.get(userId).containsKey(taskId)) {
      return inMemoryAPI.getTaskAPI().get(taskId);
    }
    return null;
  }

  @Override
  public Set<Task> getTasks(int userId) {
    return inMemoryAPI.getTaskAPI().getBatched(userTasksMap.get(userId).keySet());
  }

  @Override
  public Set<Task> getTasksWithTags(int userId, Set<UserTask.Tag> tags) {
    return inMemoryAPI.getTaskAPI().getBatched(userTasksMap.get(userId).entrySet().stream()
        .filter(integerSetEntry -> integerSetEntry.getValue().containsAll(tags))
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet()));
  }

  @Override
  public Set<Task> getTasksWithState(int userId, Task.TaskState taskState) {
    return inMemoryAPI.getTaskAPI().getBatched(userTasksMap.get(userId).keySet().stream()
        .filter(integer -> inMemoryAPI.getTaskAPI().get(integer).getState().equals(taskState))
        .collect(Collectors.toSet()));
  }

  @Override
  public Task addTask(int userId, int taskId, Set<UserTask.Tag> tags) {
    if (userTasksMap.containsKey(userId))
      userTasksMap.get(userId).put(taskId, tags);
    else
      userTasksMap.put(userId, new HashMap<Integer, Set<UserTask.Tag>>() {{
        put(taskId, tags);
      }});
    return getTask(userId, taskId);
  }

  @Override
  public Set<UserTask.Tag> updateTaskTags(int userId, int taskId, Set<UserTask.Tag> tags) {
    if (userTasksMap.containsKey(userId) && userTasksMap.get(userId).containsKey(taskId)) {
      userTasksMap.get(userId).put(taskId, tags);
    }
    return tags;
  }

  @Override
  public Task deleteTask(int userId, int taskId) {
    if (userTasksMap.containsKey(userId)) {
      return userTasksMap.get(userId).remove(taskId) == null ? null : inMemoryAPI.getTaskAPI().get(taskId);
    }
    return null;
  }
}