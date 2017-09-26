package start.resources;

import start.api.data.User;
import start.api.impl.InMemoryAPI;

import javax.ws.rs.*;

@Path("/users")
public class UserResource {

  private final InMemoryAPI inMemoryAPI;

  public UserResource(InMemoryAPI inMemoryAPI) {
    this.inMemoryAPI = inMemoryAPI;
  }

  @Path("/{userId}")
  @GET
  public User getUser(@PathParam("userId") int userId) {
    return this.inMemoryAPI.getUserAPI().get(userId);
  }

  @POST
  public User addUser(User user) {
    return this.inMemoryAPI.getUserAPI().add(user);
  }

  @Path("/{userId}")
  @PUT
  public User updateUser(@PathParam("userId") int userId, User user) {
    return this.inMemoryAPI.getUserAPI().update(userId, user);
  }

  @Path("/{userId}")
  @DELETE
  public User deleteUser(@PathParam("userId") int userId) {
    return this.inMemoryAPI.getUserAPI().delete(userId);
  }
}
