package start.resources;

import start.access.TaskDAO;
import start.model.Task;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {
  private final TaskDAO dao;

  public TaskResource(TaskDAO dao) {
    this.dao = dao;
  }

  @Path("/{taskId}")
  @GET
  public Task getTask(@PathParam("taskId") int taskId) {
    return this.dao.get(taskId);
  }

  @POST
  public Task addTask(Task task) {
    return this.dao.add(task);
  }

  @Path("/{taskId}")
  @PUT
  public Task updateTask(@PathParam("taskId") int taskId, Task task) {
    return this.dao.update(taskId, task);
  }

  @Path("/{taskId}")
  @DELETE
  public Task deleteTask(@PathParam("taskId") int taskId) {
    return this.dao.delete(taskId);
  }
}
