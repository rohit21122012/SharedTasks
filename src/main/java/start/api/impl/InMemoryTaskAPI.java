package start.api.impl;

import start.api.TaskAPI;
import start.api.data.Task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class InMemoryTaskAPI implements TaskAPI {
  private final Map<Integer, Task> taskLookup = new ConcurrentHashMap<>();
  private AtomicInteger intId = new AtomicInteger(0);

  @Override
  public Task get(int taskId) {
    return taskLookup.get(taskId);
  }

  @Override
  public Task add(Task task) {
    int id = intId.getAndIncrement();
    task.setId(id);
    taskLookup.put(id, task);
    return get(id);
  }

  @Override
  public Task update(int taskId, Task task) {
    taskLookup.replace(taskId, task);
    return get(taskId);
  }

  @Override
  public Task delete(int taskId) {
    return taskLookup.remove(taskId);
  }
}
