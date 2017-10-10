package service;

import dao.KweetDAOJPAImpl;
import dao.UserDAOJPAImpl;
import domain.Kweet;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Stateless
public class KweetService implements Serializable {
    @Inject
    private KweetDAOJPAImpl kweetDAOJPAImpl;
    @Inject
    private UserDAOJPAImpl userDAOJPA;

    public Kweet add(String kweet, User user) {
        return userDAOJPA.createKweet(kweet, new Date(), user);
    }

    public void edit(Kweet kweet) {
        kweetDAOJPAImpl.editKweet(kweet);
    }

    public void delete(Kweet kweet, User user) {
        kweetDAOJPAImpl.removeKweet(user, kweet);
    }

    public List<Kweet> getKweetBySearchString(String search) {
        return kweetDAOJPAImpl.findKweetBySearchString(search);
    }

    public List<Kweet> getKweetsByUser(User user) {
        return kweetDAOJPAImpl.findTweetsByUser(user);
    }

    public List<Kweet> getKweetsForUser(User user) {
        Iterator<User> iterator = user.getFollowers().iterator();
        List<Kweet> kweets = new ArrayList<>();
        // while loop
        while (iterator.hasNext()) {
            kweets.addAll(kweetDAOJPAImpl.findTweetsByUser(iterator.next()));
        }
        return kweets;
    }
    
}
