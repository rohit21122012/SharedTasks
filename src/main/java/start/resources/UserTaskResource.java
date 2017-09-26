package start.resources;

import start.api.data.Task;
import start.api.data.UserTask;
import start.api.impl.InMemoryAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/Users")
public class UserTaskResource {

  private final InMemoryAPI inMemoryAPI;

  public UserTaskResource(InMemoryAPI inMemoryAPI) {
    this.inMemoryAPI = inMemoryAPI;
  }


  @Path("/{userId}/tasks/{taskId}")
  @GET
  public Task getUserTask(@PathParam("userId") int userId,
                          @PathParam("taskId") int taskId,
                          @QueryParam("tags") List<UserTask.Tag> tags) {

    return inMemoryAPI.getUserTaskAPI().getTask(userId, taskId);
  }
}
