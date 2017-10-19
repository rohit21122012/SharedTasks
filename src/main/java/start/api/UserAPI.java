package start.api;

import start.data.User;

public interface UserAPI {
  User get(int userId);

  User add(User user);

  User delete(int userId);

  User update(int userId, User user);
}
