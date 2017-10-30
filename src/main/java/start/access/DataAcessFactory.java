package start.access;

import start.access.inmemory.InMemoryDAOFactoryImpl;
import start.access.zk.ZkDAOFactoryImpl;

public class DataAcessFactory {
  private static DAOFactory zkDAOFactoryImpl = null;
  private static DAOFactory inMemoryFactoryImpl = null;

  public static synchronized DAOFactory getDAOFactory(String storeName) {
    switch (storeName) {
      case "ZK":
        return zkDAOFactoryImpl == null ? new ZkDAOFactoryImpl() : zkDAOFactoryImpl;
      default:
        return inMemoryFactoryImpl == null ? new InMemoryDAOFactoryImpl() : inMemoryFactoryImpl;
    }
  }
}
