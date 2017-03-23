package boundary.rest;

import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Stateless
@Path("kwetter")
public class UserResource {
    
    @Inject
    UserService us;
    
    @GET
    @Path("allusers")
    public List<User> allUsers()
    {
        return us.getAllUsers();
    }
    
}
