package start.access;

import start.access.impl.TaskDAOImpl;
import start.access.impl.UserDAOImpl;
import start.model.Task;
import start.model.User;
import start.store.Store;

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
