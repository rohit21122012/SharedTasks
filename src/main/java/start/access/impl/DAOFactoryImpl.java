package start.access.impl;

import start.access.TaskDAO;
import start.access.UserDAO;

public class DAOFactoryImpl implements start.access.DAOFactory {
  private TaskDAO taskDAO = new InMemoryTaskDAO();
  private UserDAO userDAO = new InMemoryUserDAO();

  public UserDAO getUserDAO() {
    return userDAO;
  }

  public TaskDAO getTaskDAO() {
    return taskDAO;
  }
}
