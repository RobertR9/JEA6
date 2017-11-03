package dao;

import domain.Kweet;
import domain.User;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public interface KweetDAO {

    /**
     * @param kweetText String
     * @param postDate  Date
     * @param user      User
     * @return Kweet
     */
    Kweet createKweet(String kweetText, Date postDate, User user);

    /**
     * @param kweet Kweet
     * @return Kweet
     */
    Kweet editKweet(Kweet kweet);

    /**
     * @param user  User
     * @param kweet Kweet
     */
    void removeKweet(User user, Kweet kweet);

    /**
     * @param searchString String
     * @return List<Kweet>
     */
    List findKweetBySearchString(String searchString);

    /**
     * @param user User
     * @return List<Kweet>
     */
    List<Kweet> findKweetsByUser(User user);

    /**
     * @param username String
     * @return List<Kweet>
     */
    List findKweetsMentionedByUser(String username);
}