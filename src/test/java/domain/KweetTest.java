package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

@Ignore
public class KweetTest {
    private User henk;

    public KweetTest() {
    }

    @Before
    public void setUp() {
        henk = new User("Henk", "test", "Henk de Testman", "The Netherlands", "www.google.nl", "Houd van testen");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddKweet() throws Exception {
        assertTrue(henk.getKweets().size() == 0);
        Kweet testKweet = new Kweet(" This is a new Kweet ", new Date(), henk);
        henk.addKweet("Test kweet");
        assertTrue(henk.getKweets().size() == 1);
    }

    @Test
    public void testRemoveKweet() throws Exception {
        henk.addKweet("Test kweet one");
        Kweet removeKweet = new Kweet("Test kweet two", new Date(), henk);
        henk.addKweet(removeKweet);
        assertTrue(henk.getKweets().size() == 2);
        henk.removeKweet(removeKweet);
        assertTrue(henk.getKweets().size() == 1);
    }
}