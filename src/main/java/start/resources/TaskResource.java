package start.resources;

import start.api.InMemoryTaskAPI;
import start.api.TaskAPI;
import start.api.data.Task;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

  private TaskAPI taskAPI = new InMemoryTaskAPI();

  @Path("/{taskId}")
  @GET
  public Task getTask(@PathParam("taskId") int taskId) {
    return taskAPI.get(taskId);
  }

  @POST
  public Task addTask(Task task) {
    return taskAPI.add(task);
  }

  @Path("/{taskId}")
  @PUT
  public Task updateTask(@PathParam("taskId") int taskId, Task task) {
    return taskAPI.update(taskId, task);
  }

  @Path("/{taskId}")
  @DELETE
  public Task deleteTask(@PathParam("taskId") int taskId) {
    return taskAPI.delete(taskId);
  }
}
