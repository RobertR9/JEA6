package boundary.jsf;

import domain.Kweet;
import domain.User;
import service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    @Inject
    private UserService userService;

    public UserController() {
    }

    public User getLoggedInUser() {
        return userService.getLoggedInUser();
    }

    public List<User> getFollowers() {
        return userService.getFollowers(userService.getLoggedInUser());
    }

    public List<Kweet> getTweets() {
        return userService.getKweets(userService.getLoggedInUser());
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
