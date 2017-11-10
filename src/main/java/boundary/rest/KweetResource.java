package boundary.rest;

import domain.Kweet;
import domain.User;
import org.json.JSONException;
import org.json.JSONObject;
import service.KweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/kweets")
public class KweetResource {

    @Inject
    private KweetService kweetService;
    @Inject
    private UserService userService;

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes
    public Response addKweet(String kweet, @PathParam("id") Long id) {
        System.err.print("addkweet");
        System.err.print(kweet.toString());
        JSONObject jsonObject = null;
        String message = null;
        try {
            jsonObject = new JSONObject(kweet);
            message = jsonObject.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        User user = userService.findById(id);
        if (message == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Kweet cannot be empty").build();
        }
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found with id:" + user.getId()).build();
        }
        Kweet kwt = kweetService.add(message, user);
        return Response.ok(kwt, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editKweet(Kweet kweet) {
        if (kweet == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Kweet not found with id:" + kweet.getId()).build();
        }
        kweetService.edit(kweet);
        String json = kweet.getId().toString();
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteKweet(Kweet kweet) {
        if (kweet == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Kweet not found with id:" + kweet.getId()).build();
        }
        kweetService.delete(kweet, kweet.getOwner());

        String json = kweet.getId().toString();
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/trends")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> findTrends() {
        return kweetService.findTrends();
    }
}
