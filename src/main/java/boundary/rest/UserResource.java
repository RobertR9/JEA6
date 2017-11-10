package boundary.rest;

import domain.Kweet;
import domain.User;
import service.KweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Stateless
@Path("/users")
public class UserResource {
    @Context
    private UriInfo uriInfo;
    @Inject
    private UserService userService;
    @Inject
    private KweetService kweetService;

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

    @GET
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUser(@PathParam("id") Long id) {
        User foundUser = userService.findById(id);
        if (foundUser == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found with id:" + id).build();
        }

        return Response.ok().entity(foundUser).build();
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUser(@PathParam("username") String username) {
        User foundUser = userService.findByUsername(username);
        if (foundUser == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found with username:" + username).build();
        }

        return Response.ok().entity(foundUser).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) {
        try {
            System.err.print("username: " + username + " password: " + password);
            // Authenticate the user using the credentials provided
            this.userService.authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(username);
//            JSONObject jsonObj = new JSONObject("{\"token\":\"" + token + "\"}");
            // Return the token on the response
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

        } catch (SecurityException e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("/{id}/followers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findFollowersForUser(@PathParam("id") Long id) {

        User foundUser = userService.findById(id);
        if (foundUser == null) {
            return null;
        }
        return userService.getFollowers(foundUser);
    }

    @GET
    @Path("/{id}/following")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findFollowingForUser(@PathParam("id") Long id) {

        User foundUser = userService.findById(id);
        if (foundUser == null) {
            return null;
        }
        return userService.getFollowing(foundUser);
    }

    @GET
    @Path("/{id}/timeline")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kweet> findKweetsForUser(@PathParam("id") Long id) {

        User foundUser = userService.findById(id);
        if (foundUser == null) {
            return null;
        }
        return kweetService.getKweetsForUser(foundUser);
    }

    @GET
    @Path("/{id}/kweets")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kweet> findKweetsByUser(@PathParam("id") Long id) {

        User foundUser = userService.findById(id);
        if (foundUser == null) {
            return null;
        }
        return kweetService.getKweetsByUser(foundUser);
    }

    @GET
    @Path("/{username}/mentions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kweet> findKweetsMentionedByUser(@PathParam("username") String username) {
        return kweetService.findKweetsMentionedByUser(username);
    }

    private String issueToken(String login) {
//        Key key = keyGenerator.generateKey();
//        Date expirationDate = new Date(); //now
//        expirationDate = DateUtils.addMinutes(expirationDate, 15);
//        String jwtToken = Jwts.builder()
//                .setSubject(login)
//                .setIssuer(uriInfo.getAbsolutePath().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS512, key)
//                .compact();
//        return jwtToken;
        return login;
    }
}
