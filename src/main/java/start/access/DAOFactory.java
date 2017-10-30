package start.access;

import io.dropwizard.setup.Environment;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import start.model.Task;
import start.model.User;
import start.store.ZkManager;
import start.store.impl.ConcurrentHashMapAdaptor;
import start.store.impl.ZkStore;

public class DAOFactory {
  private static DAO zkDAOImpl = null;
  private static DAO inMemoryDAOImpl = null;

  public static synchronized DAO getDAO(String storeName, Environment environment) {
    switch (storeName) {
      case "ZK":
        return zkDAOImpl == null ? initZkDAO(environment) : zkDAOImpl;
      default:
        return inMemoryDAOImpl == null ? initInMemoryDAO() : inMemoryDAOImpl;
    }
  }

  private static DAO initInMemoryDAO() {
    return new DAO(new ConcurrentHashMapAdaptor<>(), new ConcurrentHashMapAdaptor<>());
  }

  private static DAO initZkDAO(Environment environment) {
    CuratorFramework curatorFramework =
        CuratorFrameworkFactory.newClient("localhost:2181",
            new ExponentialBackoffRetry(1000, 3));
    ZkManager zkManager = new ZkManager(curatorFramework);
    environment.lifecycle().manage(zkManager);
    return new DAO(new ZkStore<>(curatorFramework, "/user", User.class),
        new ZkStore<>(curatorFramework, "/task", Task.class)) {
    };
  }
}
