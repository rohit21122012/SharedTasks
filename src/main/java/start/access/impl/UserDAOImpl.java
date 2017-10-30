package start.access.impl;

import start.access.UserDAO;
import start.model.User;
import start.store.Store;

import java.util.concurrent.atomic.AtomicInteger;

public class UserDAOImpl implements UserDAO {
  private final Store<User> userLookup;
  private AtomicInteger intId;

  public UserDAOImpl(Store<User> userLookup) {
    this.userLookup = userLookup;
    this.intId = new AtomicInteger(0);
  }

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
