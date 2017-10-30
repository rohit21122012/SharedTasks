package start.store.impl;

import start.store.Store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapAdaptor<T> implements Store<T> {
  private Map<Integer, T> map;

  public ConcurrentHashMapAdaptor() {
    map = new ConcurrentHashMap<>();
  }

  @Override
  public T get(int id) {
    return map.get(id);
  }

  @Override
  public void put(int id, T u) {
    map.put(id, u);
  }

  @Override
  public void replace(int id, T u) {
    map.replace(id, u);
  }

  @Override
  public T remove(int id) {
    return map.remove(id);
  }
}
