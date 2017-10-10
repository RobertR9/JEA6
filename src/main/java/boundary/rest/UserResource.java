package boundary.rest;

import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Path("/test")
    public String hoi() {
        return "Hello WORLD!!!1111!!";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> findAllUsers() {
        return userService.getAllUsers();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        User userResult = userService.add(user);
        if (userResult == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found with id:" + user.getId()).build();
        }
        return Response.ok(userResult, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editUser(User user) {
        User foundUser = userService.findById(user.getId());
        if (foundUser == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found with id:" + user.getId()).build();
        }
        User userResult = userService.edit(user);
        return Response.ok(userResult, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Long id) {
        System.err.print(id);
        User foundUser = userService.findById(id);
        if (foundUser == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found with id:" + id).build();
        }
        userService.delete(foundUser);

        return Response.ok().build();
    }

}
