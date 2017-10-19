package start.access;

public interface DAOFactory {
  UserDAO getUserDAO();
  TaskDAO getTaskDAO();
}
