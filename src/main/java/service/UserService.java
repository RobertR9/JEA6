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

    private User loggedInUser;

    @Resource
    private SessionContext sessionContext;

    @Inject
    private UserDAOJPAImpl userDAOJPAImpl;
    @Inject
    private KweetDAOJPAImpl kweetDAOJPAImpl;

    public User getLoggedInUser() {
//        if (loggedInUser == null) {
//            Principal p = sessionContext.getCallerPrincipal();
//            this.loggedInUser = userDAOJPAImpl.find(p.getName());
//        }
//
//        return this.loggedInUser;
        return null;
    }

    @PermitAll
    public List<User> getFollowers(User user) {
        return userDAOJPAImpl.findAllFollowers(user);
    }

    @PermitAll
    public List<Kweet> getKweets(User user) {
        return kweetDAOJPAImpl.findTweetsByUser(user);
    }

    //    @RolesAllowed("admin")
    public List<User> getAllUsers() {
        return userDAOJPAImpl.findAllUsers();
    }

}
