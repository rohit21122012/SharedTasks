package start.api;

import start.api.data.Task;

public interface TaskAPI {
  Task get(int taskId);

  Task add(Task task);

  Task delete(int taskId);

  Task update(int taskId, Task task);
}