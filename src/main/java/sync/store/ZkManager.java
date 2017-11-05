package sync.store;

import io.dropwizard.lifecycle.Managed;
import org.apache.curator.framework.CuratorFramework;

public class ZkManager implements Managed {
  private final CuratorFramework client;

  public ZkManager(CuratorFramework client) {
    this.client = client;
  }

  @Override
  public void start() throws Exception {
    client.start();
  }

  @Override
  public void stop() throws Exception {
    client.close();
  }
}