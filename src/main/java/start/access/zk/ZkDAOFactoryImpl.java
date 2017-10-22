package start.access.zk;

import start.access.DAOFactory;
import start.access.TaskDAO;
import start.access.UserDAO;

public class ZkDAOFactoryImpl implements DAOFactory {
  private TaskDAO taskDAO = new ZkTaskDAO();
  private UserDAO userDAO = new ZkUserDAO();

  public UserDAO getUserDAO() {
    return userDAO;
  }

  public TaskDAO getTaskDAO() {
    return taskDAO;
  }
}
