package sync.access;

import io.dropwizard.setup.Environment;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import sync.Config;
import sync.model.Task;
import sync.model.User;
import sync.store.ZkManager;
import sync.store.impl.ConcurrentHashMapAdaptor;
import sync.store.impl.ZkStore;

public class DAOFactory {
  private static DAO zkDAOImpl = null;
  private static DAO inMemoryDAOImpl = null;

  public static synchronized DAO getDAO(Config.DBConfig dbConfig, Environment environment) {
    switch (dbConfig.getDbName()) {
      case "zk":
        return zkDAOImpl == null ? initZkDAO(environment, dbConfig.getZk()) : zkDAOImpl;
      default:
        return inMemoryDAOImpl == null ? initInMemoryDAO() : inMemoryDAOImpl;
    }
  }

  private static DAO initInMemoryDAO() {
    return new DAO(new ConcurrentHashMapAdaptor<>(), new ConcurrentHashMapAdaptor<>());
  }

  private static DAO initZkDAO(Environment environment, Config.DBConfig.Curator zk) {
    CuratorFramework curatorFramework =
        CuratorFrameworkFactory.newClient(zk.getConnectString(),
            new ExponentialBackoffRetry(zk.getBaseSleepTimeMs(), zk.getMaxRetries()));
    ZkManager zkManager = new ZkManager(curatorFramework);
    environment.lifecycle().manage(zkManager);
    return new DAO(new ZkStore<>(curatorFramework, "/user", User.class),
        new ZkStore<>(curatorFramework, "/task", Task.class)) {
    };
  }
}
