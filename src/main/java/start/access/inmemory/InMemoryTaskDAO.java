package start.access.inmemory;

import start.access.TaskDAO;
import start.model.Task;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class InMemoryTaskDAO implements TaskDAO {
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

  @Override
  public Set<Task> getBatched(Set<Integer> taskIds) {
    return taskIds.stream().map(taskLookup::get).collect(Collectors.toSet());
  }

  @Override
  public Set<Task> deleteBatched(Set<Integer> taskIds) {
    return taskIds.stream().map(taskLookup::remove).collect(Collectors.toSet());
  }
}
