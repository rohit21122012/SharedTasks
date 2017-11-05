package sync.access;

import sync.access.impl.TaskDAOImpl;
import sync.access.impl.UserDAOImpl;
import sync.model.Task;
import sync.model.User;
import sync.store.Store;

public class DAO {
  private final TaskDAO taskDAO;
  private final UserDAO userDAO;

  DAO(Store<User> userZkStore, Store<Task> taskZkStore) {
    userDAO = new UserDAOImpl(userZkStore);
    taskDAO = new TaskDAOImpl(taskZkStore);
  }

  public UserDAO getUserDAO() {
    return userDAO;
  }

  public TaskDAO getTaskDAO() {
    return taskDAO;
  }
}
