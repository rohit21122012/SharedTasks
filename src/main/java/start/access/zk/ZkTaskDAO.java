package start.access.zk;

import start.access.TaskDAO;
import start.model.Task;

import java.util.Set;

public class ZkTaskDAO implements TaskDAO {
  @Override
  public Task get(int taskId) {
    return null;
  }

  @Override
  public Task add(Task task) {
    return null;
  }

  @Override
  public Task update(int taskId, Task task) {
    return null;
  }

  @Override
  public Task delete(int taskId) {
    return null;
  }

  @Override
  public Set<Task> getBatched(Set<Integer> taskIds) {
    return null;
  }

  @Override
  public Set<Task> deleteBatched(Set<Integer> taskIds) {
    return null;
  }
}
