package start.access.zk;

import org.apache.curator.x.async.modeled.ModeledFramework;
import start.access.UserDAO;
import start.model.User;

import java.util.concurrent.atomic.AtomicInteger;

public class ZkUserDAO implements UserDAO {
  private final ModeledFramework<User> modeledClient;
  private AtomicInteger intId = new AtomicInteger(0);

  ZkUserDAO(ModeledFramework<User> modeledClient) {
    this.modeledClient = modeledClient;
  }

  @Override
  public User get(int userId) {
    return modeledClient.child(userId).read().whenComplete((user, throwable) -> {

    })
  }

  @Override
  public User add(User user) {
    int id = intId.getAndIncrement();
    user.setId(id);
    modeledClient.child(id).set(user);
    return get(id);
  }

  @Override
  public User delete(int userId) {
    return null;
  }

  @Override
  public User update(int userId, User user) {
    return null;
  }
}
