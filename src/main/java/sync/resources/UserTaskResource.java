package sync.resources;

import sync.api.UserTaskAPI;
import sync.model.Tag;
import sync.model.Task;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/Users")
public class UserTaskResource {

  private final UserTaskAPI userTaskAPI;

  public UserTaskResource(UserTaskAPI api) {
    this.userTaskAPI = api;
  }

  @Path("/{userId}/tasks/{taskId}")
  @GET
  public Task getUserTask(@PathParam("userId") int userId,
                          @PathParam("taskId") int taskId,
                          @QueryParam("tags") List<Tag> tags) {
    return userTaskAPI.getTask(userId, taskId);
  }
}
