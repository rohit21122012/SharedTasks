package start.store.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.curator.framework.CuratorFramework;
import start.store.Store;

public class ZkStore<U> implements Store<U> {
  private final CuratorFramework zkClient;
  private final String path;
  private final Class<U> clazz;
  private ObjectMapper objectMapper;

  public ZkStore(CuratorFramework curatorFramework, String path, Class<U> clazz) {
    this.zkClient = curatorFramework;
    this.path = path;
    this.clazz = clazz;
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public U get(int id) {
    try {
      return objectMapper.readValue(zkClient.getData().forPath(path + "/" + String.valueOf(id)), clazz);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void put(int id, U u) {
    try {
      zkClient.create().orSetData().creatingParentsIfNeeded().forPath(path + "/" + String.valueOf(id), objectMapper.writeValueAsBytes(u));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void replace(int id, U u) {
    try {
      zkClient.setData().forPath(path + "/" + String.valueOf(id), objectMapper.writeValueAsBytes(u));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public U remove(int id) {
    U toBeRemoved = get(id);
    try {
      zkClient.delete().forPath(path + "/" + String.valueOf(id));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return toBeRemoved;
  }

}
