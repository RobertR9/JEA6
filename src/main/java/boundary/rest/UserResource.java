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
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        userService.add(user);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found with id:" + user.getId()).build();
        }
        String json = user.getId().toString();
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editUser(User user) {
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found with id:" + user.getId()).build();
        }
        userService.edit(user);
        String json = user.getId().toString();
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(User user) {
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found with id:" + user.getId()).build();
        }
        userService.delete(user);

        String json = user.getId().toString();
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/get")
    @Produces("application/json")
    public User getProductInJSON() {

        User product = new User();
        product.setName("iPad 3");
        product.setId(999L);

        return product;

    }

}
