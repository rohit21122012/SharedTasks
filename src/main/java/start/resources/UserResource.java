package start.resources;

import start.access.DAOFactory;
import start.model.User;

import javax.ws.rs.*;

@Path("/users")
public class UserResource {

  private final DAOFactory DAOFactory;

  public UserResource(DAOFactory DAOFactory) {
    this.DAOFactory = DAOFactory;
  }

  @Path("/{userId}")
  @GET
  public User getUser(@PathParam("userId") int userId) {
    return this.DAOFactory.getUserDAO().get(userId);
  }

  @POST
  public User addUser(User user) {
    return this.DAOFactory.getUserDAO().add(user);
  }

  @Path("/{userId}")
  @PUT
  public User updateUser(@PathParam("userId") int userId, User user) {
    return this.DAOFactory.getUserDAO().update(userId, user);
  }

  @Path("/{userId}")
  @DELETE
  public User deleteUser(@PathParam("userId") int userId) {
    return this.DAOFactory.getUserDAO().delete(userId);
  }
}
