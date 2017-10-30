package start.access.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.async.AsyncCuratorFramework;
import org.apache.curator.x.async.modeled.JacksonModelSerializer;
import org.apache.curator.x.async.modeled.ModelSpec;
import org.apache.curator.x.async.modeled.ModeledFramework;
import org.apache.curator.x.async.modeled.ZPath;
import start.access.DAOFactory;
import start.access.TaskDAO;
import start.access.UserDAO;
import start.model.Task;
import start.model.User;

public class ZkDAOFactoryImpl implements DAOFactory {
  private final UserDAO userDAO;
  private TaskDAO taskDAO;

  ZkDAOFactoryImpl() {
    ZPath path = ZPath.from("/ut/{obj}/{id}");
    ModelSpec<User> userModelSpec = ModelSpec.builder(path.resolved("user"), JacksonModelSerializer.build(User.class)).build();
    ModelSpec<Task> taskModelSpec = ModelSpec.builder(path.resolved("task"), JacksonModelSerializer.build(Task.class)).build();
    CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3));
    AsyncCuratorFramework async = AsyncCuratorFramework.wrap(client);
    userDAO = new ZkUserDAO(ModeledFramework.wrap(async, userModelSpec));
    taskDAO = new ZkTaskDAO(ModeledFramework.wrap(async, taskModelSpec));
  }
  public UserDAO getUserDAO() {
    return userDAO;
  }

  public TaskDAO getTaskDAO() {
    return taskDAO;
  }
}
