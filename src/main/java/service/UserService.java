package service;

import dao.KweetDAOJPAImpl;
import dao.UserDAOJPAImpl;
import domain.Kweet;
import domain.User;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;

@Stateless
public class UserService implements Serializable {

    @Inject
    private UserDAOJPAImpl userDAOJPAImpl;
    @Inject
    private KweetDAOJPAImpl kweetDAOJPAImpl;

    public List<User> getFollowers(User user) {
        return userDAOJPAImpl.findAllFollowers(user);
    }

    public List<Kweet> getKweets(User user) {
        return kweetDAOJPAImpl.findTweetsByUser(user);
    }

    public List<User> getAllUsers() {
        return userDAOJPAImpl.findAllUsers();
    }
}
