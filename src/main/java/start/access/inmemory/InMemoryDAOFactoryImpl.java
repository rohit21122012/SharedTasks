package start.access.inmemory;

import start.access.DAOFactory;
import start.access.TaskDAO;
import start.access.UserDAO;

public class InMemoryDAOFactoryImpl implements DAOFactory {
  private TaskDAO taskDAO = new InMemoryTaskDAO();
  private UserDAO userDAO = new InMemoryUserDAO();

  public UserDAO getUserDAO() {
    return userDAO;
  }

  public TaskDAO getTaskDAO() {
    return taskDAO;
  }
}
