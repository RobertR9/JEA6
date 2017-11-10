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
public class KweetDAOJPAImpl implements KweetDAO {

    @PersistenceContext(name = "KwetterPU")
    private EntityManager em;

    /**
     * @param kweetText String
     * @param postDate  Date
     * @param user      User
     * @return Kweet
     */
    @Override
    public Kweet createKweet(String kweetText, Date postDate, User user) {
        Kweet kweet = new Kweet(kweetText, postDate, user);
        this.em.persist(kweet);
        return kweet;
    }

    /**
     * @param kweet Kweet
     * @return Kweet
     */
    @Override
    public Kweet editKweet(Kweet kweet) {
        em.merge(kweet);
        return kweet;
    }

    /**
     * @param user  User
     * @param kweet Kweet
     */
    @Override
    public void removeKweet(User user, Kweet kweet) {
        user.removeKweet(kweet);
        em.remove(kweet);
    }

    /**
     * @param searchString String
     * @return List<Kweet>
     */
    @Override
    public List<Kweet> findKweetBySearchString(String searchString) {
        Query q = em.createNamedQuery("Kweet.findBySearchString");
        q.setParameter("searchString", "%" + searchString + "%");
        return q.getResultList();
    }

    /**
     * @param user User
     * @return List<Kweet>
     */
    @Override
    public List<Kweet> findKweetsByUser(User user) {
        Query q = em.createNamedQuery("Kweet.findAllByUser");
        q.setParameter("user", user);
        List<Kweet> kweets = q.getResultList();
        return kweets;
    }

    /**
     * @param username String
     * @return List<Kweet>
     */
    @Override
    public List<Kweet> findKweetsMentionedByUser(String username) {
        Query q = em.createNamedQuery("Kweet.findBySearchString");
        q.setParameter("searchString", "%@" + username + "%");
        return q.getResultList();
    }

    /**
     * @return List<String>
     */
    @Override
    public List<String> findTrends() {
        Query q = em.createNamedQuery("Kweet.findTrends");
        q.setParameter("searchString", "#%");
        return q.getResultList();
    }


}