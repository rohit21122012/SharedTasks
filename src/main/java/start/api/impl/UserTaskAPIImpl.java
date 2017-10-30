package start.api.impl;

import start.access.DAO;
import start.api.UserTaskAPI;
import start.model.Tag;
import start.model.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class UserTaskAPIImpl implements UserTaskAPI {
  private final DAO dao;
  private Map<Integer, Map<Integer, Set<Tag>>> userTasksMap = new ConcurrentHashMap<>();

  public UserTaskAPIImpl(DAO dao) {
    this.dao = dao;
  }

  @Override
  public Task getTask(int userId, int taskId) {
    if (userTasksMap.get(userId).containsKey(taskId)) {
      return dao.getTaskDAO().get(taskId);
    }
    return null;
  }

  @Override
  public Set<Task> getTasks(int userId) {
    return dao.getTaskDAO().getBatched(userTasksMap.get(userId).keySet());
  }

  @Override
  public Set<Task> getTasksWithTags(int userId, Set<Tag> tags) {
    return dao.getTaskDAO().getBatched(userTasksMap.get(userId).entrySet().stream()
        .filter(integerSetEntry -> integerSetEntry.getValue().containsAll(tags))
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet()));
  }

  @Override
  public Set<Task> getTasksWithState(int userId, Task.TaskState taskState) {
    return dao.getTaskDAO().getBatched(userTasksMap.get(userId).keySet().stream()
        .filter(integer -> dao.getTaskDAO().get(integer).getState().equals(taskState))
        .collect(Collectors.toSet()));
  }

  @Override
  public Task addTask(int userId, int taskId, Set<Tag> tags) {
    if (userTasksMap.containsKey(userId))
      userTasksMap.get(userId).put(taskId, tags);
    else
      userTasksMap.put(userId, new HashMap<Integer, Set<Tag>>() {{
        put(taskId, tags);
      }});
    return getTask(userId, taskId);
  }

  @Override
  public Set<Tag> updateTaskTags(int userId, int taskId, Set<Tag> tags) {
    if (userTasksMap.containsKey(userId) && userTasksMap.get(userId).containsKey(taskId)) {
      userTasksMap.get(userId).put(taskId, tags);
    }
    return tags;
  }

  @Override
  public Task deleteTask(int userId, int taskId) {
    if (userTasksMap.containsKey(userId)) {
      return userTasksMap.get(userId).remove(taskId) == null ? null : dao.getTaskDAO().get(taskId);
    }
    return null;
  }
}