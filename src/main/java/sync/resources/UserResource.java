package sync.resources;

import sync.access.UserDAO;
import sync.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

  private final UserDAO dao;

  public UserResource(UserDAO dao) {
    this.dao = dao;
  }

  @Path("/{userId}")
  @GET
  public User getUser(@PathParam("userId") int userId) {
    return this.dao.get(userId);
  }

  @POST
  public User addUser(User user) {
    return this.dao.add(user);
  }

  @Path("/{userId}")
  @PUT
  public User updateUser(@PathParam("userId") int userId, User user) {
    return this.dao.update(userId, user);
  }

  @Path("/{userId}")
  @DELETE
  public User deleteUser(@PathParam("userId") int userId) {
    return this.dao.delete(userId);
  }
}
