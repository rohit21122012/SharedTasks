package start.access;

import start.model.User;

public interface UserDAO {
  User get(int userId);
  User add(User user);
  User delete(int userId);
  User update(int userId, User user);
}
