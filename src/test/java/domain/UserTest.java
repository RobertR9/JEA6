package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore
public class UserTest {

    private static List<User> userList;
    private static List<User> userFollowingList;

    public UserTest() {
    }

    @Before
    public void setUp() {
        userList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            userList.add(new User("Test" + i, "test+i", "Test+i Test", "The Netherlands", "www.google.nl", "test user" + i, null));
        }

        userFollowingList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            userFollowingList.add(new User("Test" + i, "test+i", "Test+i Test", "The Netherlands", "www.google.nl", "test user" + i, null));
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User("Henk", "test", "Henkie", "The Netherlands", "www.google.nl", "test user", null);
        userList.add(user);
        assertEquals(11, userList.size());
    }

    @Test
    public void testAddFollowing() throws Exception {
        User henk = userList.get(9);
        assertTrue(henk.getFollowers().size() == 0);
        henk.addFollower(userList.get(0));
        assertTrue(henk.getFollowers().size() == 1);
        assertTrue(henk.getFollowers().contains(userList.get(0)));
    }

    @Test
    public void testRemoveFollower() throws Exception {
        User henk = userList.get(9);
        User follower = userList.get(0);
        henk.addFollower(follower);
        assertTrue(henk.getFollowers().size() == 1);
        assertTrue(henk.getFollowers().contains(userList.get(0)));
        henk.deleteFollower(follower);
        assertTrue(henk.getFollowers().size() == 0);
    }
}