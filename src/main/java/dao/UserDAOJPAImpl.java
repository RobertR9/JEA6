package dao;

import domain.Kweet;
import domain.User;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@RequestScoped
public class UserDAOJPAImpl implements UserDAO {

    @PersistenceContext(name = "KwetterPU")
    private EntityManager em;

    /**
     * Method to create users for testing purposes.
     */
    @Override
    public void initUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @param user User
     * @return User
     */
    @Override
    public User createUser(User user) {
        em.persist(user);
        return user;
    }

    /**
     * @param user User
     * @return User
     */
    @Override
    public User editUser(User user) {
        em.merge(user);
        return user;
    }

    /**
     * @param user User
     */
    @Override
    public void removeUser(User user) {
        em.remove(user);
    }

    /**
     * @param id Long
     * @return User
     */
    @Override
    public User find(Long id) {
        Query q = em.createNamedQuery("User.findById");
        q.setParameter("id", id);
        return (User) q.getSingleResult();
    }

    /**
     * @param username String
     * @return User
     */
    @Override
    public User find(String username) {
        Query q = em.createNamedQuery("User.findByUsername");
        q.setParameter("username", username);
        return (User) q.getSingleResult();
    }

    /**
     * @return List<User>
     */
    @Override
    public List<User> findAllUsers() {
        Query q = em.createNamedQuery("User.findAll");
        List<User> users = q.getResultList();
        return users;
    }

    /**
     * Returns a list of Users which the given user is following.
     *
     * @param user User
     * @return List<User>
     */
    @Override
    public List<User> findAllFollowers(User user) {
        Query q = em.createNamedQuery("User.findFollowers");
        q.setParameter("user", user);
        List<User> followers = q.getResultList();
        return followers;
    }

    /**
     * @param user     User
     * @param follower User
     */
    @Override
    public void addFollower(User user, User follower) {
        user.addFollower(follower);
        em.merge(user);
    }

    /**
     * @param user     User
     * @param follower User
     */
    @Override
    public void removeFollower(User user, User follower) {
        user.deleteFollower(follower);
        em.merge(user);
    }

    public Kweet createKweet(String kweetText, Date postDate, User user) {
        Kweet kweet = user.addKweet(kweetText);
        this.em.merge(user);
        return kweet;
    }

    /**
     * This is for testing purposes.
     *
     * @param em EntityManager
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
