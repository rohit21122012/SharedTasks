package start.api;

import start.data.Task;

import java.util.Set;

public interface TaskAPI {
  Task get(int taskId);
  Task add(Task task);
  Task update(int taskId, Task task);

  Task delete(int taskId);

  Set<Task> getBatched(Set<Integer> taskIds);

  Set<Task> deleteBatched(Set<Integer> taskIds);
}