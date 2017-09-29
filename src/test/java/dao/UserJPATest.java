package dao;

import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserJPATest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private UserDAOJPAImpl userDAO;

    public UserJPATest() {

    }

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(UserJPATest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDAO = new UserDAOJPAImpl();
        userDAO.setEm(em);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void savingUserSuccessful() {
        Integer expectedResult = 1;
        User user = new User("Henk", "test", "Henk de Testman", "The Netherlands", "www.google.nl", "Houd van testen");
        tx.begin();
        userDAO.createUser(user);
        tx.commit();
        assertTrue(userDAO.findAllUsers().size() == expectedResult);
    }

    @Test
    public void findUserSuccessfull() {
        User user = new User("Henk", "test", "Henk de Testman", "The Netherlands", "www.google.nl", "Houd van testen");
        tx.begin();
        userDAO.createUser(user);
        tx.commit();
        User usr = userDAO.find(user.getId());
        assertEquals(usr, user);
    }

    @Test
    public void followerTest() {
        User henk = new User("Henk", "test", "Henk de Testman", "The Netherlands", "www.google.nl", "Houd van testen");
        User bert = new User("Bert", "test", "Bert de Testman", "The Netherlands", "www.google.nl", "Houd van testen");
        User klaas = new User("Klaas", "test", "Klaas de Testman", "The Netherlands", "www.google.nl", "Houd van testen");
        tx.begin();
        userDAO.createUser(henk);
        userDAO.createUser(bert);
        userDAO.createUser(klaas);
        tx.commit();
        tx.begin();
        userDAO.addFollower(henk,bert);
        userDAO.addFollower(henk,klaas);
        tx.commit();
        tx.begin();
        List<User> followers = userDAO.findAllFollowers(henk);
        assertTrue(followers.contains(bert) && followers.contains(klaas));
        tx.commit();
        tx.begin();
        userDAO.removeFollower(henk, klaas);
        tx.commit();
        tx.begin();
        followers = userDAO.findAllFollowers(henk);
        assertTrue(followers.size() == 1 && !followers.contains(klaas));
    }
}
