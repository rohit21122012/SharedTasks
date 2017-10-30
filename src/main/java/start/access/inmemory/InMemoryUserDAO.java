package start.access.inmemory;

import start.access.UserDAO;
import start.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class InMemoryUserDAO implements UserDAO {
  private final Map<Integer, User> userLookup = new ConcurrentHashMap<>();
  private AtomicInteger intId = new AtomicInteger(0);

  @Override
  public User get(int userId) {
    return userLookup.get(userId);
  }

  @Override
  public User add(User user) {
    int id = intId.getAndIncrement();
    user.setId(id);
    userLookup.put(id, user);
    return get(id);
  }

  @Override
  public User update(int userId, User task) {
    userLookup.replace(userId, task);
    return get(userId);
  }

  @Override
  public User delete(int userId) {
    return userLookup.remove(userId);
  }
}
