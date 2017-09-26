package start.resources;

import start.api.data.Task;
import start.api.impl.InMemoryAPI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {
  private final InMemoryAPI inMemoryAPI;

  public TaskResource(InMemoryAPI inMemoryAPI) {
    this.inMemoryAPI = inMemoryAPI;
  }

  @Path("/{taskId}")
  @GET
  public Task getTask(@PathParam("taskId") int taskId) {
    return this.inMemoryAPI.getTaskAPI().get(taskId);
  }

  @POST
  public Task addTask(Task task, @QueryParam("createdBy") int userId) {

    return this.inMemoryAPI.getTaskAPI().add(task);
  }

  @Path("/{taskId}")
  @PUT
  public Task updateTask(@PathParam("taskId") int taskId, Task task) {
    return this.inMemoryAPI.getTaskAPI().update(taskId, task);
  }

  @Path("/{taskId}")
  @DELETE
  public Task deleteTask(@PathParam("taskId") int taskId) {
    return this.inMemoryAPI.getTaskAPI().delete(taskId);
  }
}
