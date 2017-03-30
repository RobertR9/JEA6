package service;

import dao.UserDAO;
import domain.Tweet;
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
    private UserDAO userDAO;

    public User getLoggedInUser() {
        if (loggedInUser == null) {
            Principal p = sessionContext.getCallerPrincipal();
            this.loggedInUser = userDAO.getUserByUsername(p.getName());
        }

        return this.loggedInUser;
    }

    @PermitAll
    public List<User> getFollowers(User user) {
        return userDAO.getFollowers(user);
    }

    @PermitAll
    public List<Tweet> getTweets(User user) {
        return userDAO.getTweets(user);
    }

    //    @RolesAllowed("admin")
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void setDAO(UserDAO dao) {
        this.userDAO = dao;
    }
}
