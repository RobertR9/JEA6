package boundary.rest;

import domain.Kweet;
import domain.User;
import service.KweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/kweets")
public class KweetResource {

    @Inject
    private KweetService kweetService;

    @GET
    @Path("/test")
    public String hoi() {
        return "Hello WORLD!!!1111!!";
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addKweet(String kweet, User user) {
        kweetService.add(kweet, user);
        if (kweet == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Kweet cannot be empty").build();
        }
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found with id:" + user.getId()).build();
        }
        String json = kweet;
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/edit")
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
    @Path("/delete")
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
}
