package start.resources;

import start.access.DAOFactory;
import start.model.Task;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {
  private final DAOFactory DAOFactory;

  public TaskResource(DAOFactory DAOFactory) {
    this.DAOFactory = DAOFactory;
  }

  @Path("/{taskId}")
  @GET
  public Task getTask(@PathParam("taskId") int taskId) {
    return this.DAOFactory.getTaskDAO().get(taskId);
  }

  @POST
  public Task addTask(Task task, @QueryParam("createdBy") int userId) {

    return this.DAOFactory.getTaskDAO().add(task);
  }

  @Path("/{taskId}")
  @PUT
  public Task updateTask(@PathParam("taskId") int taskId, Task task) {
    return this.DAOFactory.getTaskDAO().update(taskId, task);
  }

  @Path("/{taskId}")
  @DELETE
  public Task deleteTask(@PathParam("taskId") int taskId) {
    return this.DAOFactory.getTaskDAO().delete(taskId);
  }
}
