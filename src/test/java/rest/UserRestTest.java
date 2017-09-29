package rest;

import domain.User;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
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

    private Client client;
    private WebTarget root;
    private static final String PATH = "/api/users/";
    private static final String BASEURL = "http://localhost:8080" + PATH;

    public UserRestTest() {
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(User.class);
    }

//    @Before
//    public void setUp() {
//        this.client = ClientBuilder.newClient();
//        this.root = this.client.target(BASEURL);
//    }

    @Test
    public void ordersPathParamTest() {
        WebTarget target = ClientBuilder.newClient().target(BASEURL).path("test");
        System.err.println(BASEURL + "test");
        String response =target.request().get(String.class);
        System.err.println(response);
//        Assert.assertTrue("orderId: 453".equals(response));
    }
//    @Test
//    public void test() {
//        String mediaType = MediaType.APPLICATION_JSON;
//        this.root = this.client.target(BASEURL + "test");
//        System.err.println(client.target().toString());
//        String result = this.root.request().get(String.class);
//
//        System.err.println(result);
//    }
//
//    @Test
//    public void crud() {
//        String mediaType = MediaType.APPLICATION_JSON;
//        User user = new User("Bert", "bert", "bert test", "eindhoven", "www.fontys.nl", "Sportvissen, jeu de boules, kienen");
//
//        final Entity<User> entity = Entity.entity(user, mediaType);
//        User studResult = this.root.request().post(entity, User.class);
//        assertThat(studResult, is(user));


        //
//        String mediaType = MediaType.APPLICATION_JSON;
//        Student leider = new Student("Leider", 100, "Java");
//        Student volger = new Student("Volger", 100, "Java");
//        StudentDTO studentDTO = new StudentDTO(leider, volger);
//
//        final Entity<StudentDTO> entity = Entity.entity(studentDTO, mediaType);
//        Student studResult = this.root.request().post(entity, Student.class);
//        System.out.println("NAME " + studResult.getName());
//          
//          
//          assertThat(studResult, is(leider));
//          
//          
        
      /*
        final String mediaType = MediaType.APPLICATION_JSON;
        final Entity<Student> entity = Entity.entity(student, mediaType);
        Response response = this.root.request().post(entity, Response.class);
        Student studResult = response.readEntity(Student.class);
        assertThat(response.getStatus(), is(200));
        assertThat(studResult, is(student));
        
        response = this.root.path(student.getName()).request(mediaType).delete(Response.class);
        assertThat(response.getStatus(), is(204));
        */
//    }

}