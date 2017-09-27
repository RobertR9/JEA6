package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore
public class KweetTest {

    private static List<User> userList;
    private static List<User> userFollowingList;

    public KweetTest() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddUser() throws Exception {

    }

    @Test
    public void testAddFollowing() throws Exception {
        int i = 9;
        for (User follower : userFollowingList) {
            userList.get(i).addFollowing(follower);
            i--;
        }

        assertTrue(userList.get(9).getFollowing().contains(userFollowingList.get(0)));
        assertTrue(userList.get(0).getFollowing().contains(userFollowingList.get(9)));
    }

    @Test
    public void testAddKweet() throws Exception {

        int i;
        String newLine = System.getProperty("line.separator");

        for (i = 0; i < 10; i++) {
            Kweet testKweet = new Kweet(" This is a new Kweet " + i, new Date(), userList.get(1));
            userList.get(1).addKweet(testKweet);
        }

        for (Kweet kweet : userList.get(1).getKweets()) {
            System.out.print(kweet.getKweet() + " Posted By:" + kweet.getOwner().getUsername() + newLine + "on: " + kweet.getPostDate());
        }
    }
}