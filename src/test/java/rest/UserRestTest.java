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
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserRestTest extends JerseyTest {

    public UserRestTest() {
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(UserResource.class);
    }

    Client client;
    WebTarget root;
    static final String PATH = "/kwetter/api/users/test";
    static final String BASEURL = "http://localhost:8080" + PATH;

    @Before
    public void setUp() {
        this.client = ClientBuilder.newClient();
        this.root = this.client.target(BASEURL);
    }

    @Test
    public void test() {
        String result = this.root.request().get(String.class);
        System.err.println(result);
        assertThat(result, is("Hello WORLD!!!1111!!"));
    }

    @Test
    public void addUserTest(){
        String mediaType = MediaType.APPLICATION_JSON;
        User user = new User("Henk", "test", "Henk de Testman", "The Netherlands", "www.google.nl", "Houd van testen");

        final Entity<User> entity = Entity.entity(user,mediaType);
        User userResult = this.client.target(BASEURL + "/add").request().post(entity, User.class);
        assertThat(userResult, is(user));
    }
}