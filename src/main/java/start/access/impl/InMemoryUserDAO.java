package start.access.impl;

import start.access.UserDAO;
import start.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class InMemoryUserDAO implements UserDAO {
  private final Map<Integer, User> userLookup = new ConcurrentHashMap<>();
  private AtomicInteger intId = new AtomicInteger(0);

  @Override
  public User get(int taskId) {
    return userLookup.get(taskId);
  }

  @Override
  public User add(User task) {
    int id = intId.getAndIncrement();
    task.setId(id);
    userLookup.put(id, task);
    return get(id);
  }

  @Override
  public User update(int taskId, User task) {
    userLookup.replace(taskId, task);
    return get(taskId);
  }

  @Override
  public User delete(int taskId) {
    return userLookup.remove(taskId);
  }
}