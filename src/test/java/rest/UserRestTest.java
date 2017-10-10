package rest;

import boundary.rest.UserResource;
import domain.User;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserRestTest extends JerseyTest {

    public UserRestTest() {
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(UserResource.class);
    }

    private Client client;
    private static final String PATH = "/kwetter/api/users";
    private static final String BASEURL = "http://localhost:8080" + PATH;

    @Before
    public void setUp() {
        this.client = ClientBuilder.newClient();
    }

    @Test
    public void test() {
        String result = this.client.target(BASEURL + "/test").request().get(String.class);

        assertThat(result, is("Hello WORLD!!!1111!!"));
    }

    @Test
    public void crud() {
        String mediaType = MediaType.APPLICATION_JSON;
        User user = new User("Henk", "test", "Henk de Testman", "The Netherlands", "www.google.nl", "Houd van testen");

        final Entity<User> userEntity = Entity.entity(user, mediaType);
        Response response = this.client.target(BASEURL).request().post(userEntity, Response.class);
        User userRes = response.readEntity(User.class);
        assertThat(response.getStatus(), is(200));
        assertThat(userRes.getUsername(), is(user.getUsername()));

        userRes.setBio("change");
        final Entity<User> editUser = Entity.entity(userRes, mediaType);

        Response editResponse = this.client.target(BASEURL).request().put(editUser, Response.class);
        User userResult = editResponse.readEntity(User.class);
        assertThat(response.getStatus(), is(200));
        assertThat(userResult.getBio(), is("change"));
    }

}