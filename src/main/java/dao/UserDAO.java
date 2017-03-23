package dao;

import domain.Tweet;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDAO {

    @PersistenceContext(name = "KwetterPU")
    public EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User getUserByUsername(String username) {
        Query q = em.createNamedQuery("User.getUserByUsername");
        q.setParameter("username", username);
        return (User) q.getSingleResult();
    }

    public List<User> getFollowers(User user) {
        Query q = em.createNamedQuery("User.getFollowersByUser");
        q.setParameter("user", user);
        return q.getResultList();
    }

    public List<Tweet> getTweets(User user) {
        Query q = em.createNamedQuery("User.getTweetsByUser");
        q.setParameter("user", user);
        return q.getResultList();
    }

    public List<User> getAllUsers() {
        Query q = em.createNamedQuery("User.getAllUsers");
        return q.getResultList();
    }

}
