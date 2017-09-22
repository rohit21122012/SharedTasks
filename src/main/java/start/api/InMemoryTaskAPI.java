package start.api;

import start.api.data.Task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class InMemoryTaskAPI implements TaskAPI {
  private final Map<Integer, Task> taskMap = new ConcurrentHashMap<>();
  private AtomicInteger intId = new AtomicInteger(0);

  @Override
  public Task get(int taskId) {
    return taskMap.get(taskId);
  }

  @Override
  public Task add(Task task) {
    int id = intId.getAndIncrement();
    task.setId(id);
    taskMap.put(id, task);
    return get(id);
  }

  @Override
  public Task update(int taskId, Task task) {
    taskMap.replace(taskId, task);
    return get(taskId);
  }

  @Override
  public Task delete(int taskId) {
    return taskMap.remove(taskId);
  }
}
