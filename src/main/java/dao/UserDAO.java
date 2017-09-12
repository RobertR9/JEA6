package dao;

import domain.Kweet;
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

    public User find(String username) {
        Query q = em.createNamedQuery("User.findByUsername");
        q.setParameter("username", username);
        return (User) q.getSingleResult();
    }

    public User find(Long id) {
        Query q = em.createNamedQuery("User.findById");
        q.setParameter("id", id);
        return (User) q.getSingleResult();
    }

    public List<User> getFollowers(User user) {
        Query q = em.createNamedQuery("User.findFollowers");
        q.setParameter("user", user);
        return q.getResultList();
    }

    public List<Kweet> getTweets(User user) {
        Query q = em.createNamedQuery("User.findAllKweets");
        q.setParameter("user", user);
        return q.getResultList();
    }

    public List<User> getAllUsers() {
        Query q = em.createNamedQuery("User.findAll");
        return q.getResultList();
    }

}
