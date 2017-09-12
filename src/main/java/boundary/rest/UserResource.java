package boundary.rest;

import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Stateless
@Path("user")
public class UserResource {

    @Inject
    UserService us;

    @GET
    @Path("/all")
    public List<User> allUsers() {
        return us.getAllUsers();
    }


    @GET
    @Path("/test")
    public String hoi() {
        return "Hello WORLD!!!1111!!";
    }

}
