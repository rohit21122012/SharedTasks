package start.api;

import start.api.data.User;

public interface UserAPI {
  User get(int userId);

  User add(User user);

  User delete(int userId);

  User update(int userId, User user);
}
