package start.api.impl;

import start.api.TaskAPI;
import start.api.UserAPI;
import start.api.UserTaskAPI;

public class InMemoryAPI {
  private TaskAPI taskAPI = new InMemoryTaskAPI();
  private UserAPI userAPI = new InMemoryUserAPI();
  private UserTaskAPI userTaskAPI = new InMemoryUserTaskAPI();

  public UserAPI getUserAPI() {
    return userAPI;
  }

  public TaskAPI getTaskAPI() {
    return taskAPI;
  }

  public UserTaskAPI getUserTaskAPI() {
    return userTaskAPI;
  }
}
