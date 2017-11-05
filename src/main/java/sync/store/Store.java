package sync.store;

public interface Store<T> {
  T get(int id);

  void put(int id, T t);

  void replace(int id, T t);

  T remove(int id);
}
