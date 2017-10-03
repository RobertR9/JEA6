package boundary.controllers;

import domain.Kweet;
import domain.User;
import service.KweetService;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Named("ProfileController")
@ManagedBean
@SessionScoped
public class ProfileController implements Serializable {
    @Inject
    private KweetService kweetService;
    @Inject
    private UserService userService;
    @Inject
    private LoginController loginController;

    private List<Kweet> kweets;
    private List<User> following;
    private List<User> followers;
    private User user;
    private Kweet kweet;

    public ProfileController() {
    }

    @PostConstruct
    public void init() {
        System.err.println("Init \n");
        System.err.println(loginController.getUser());
        this.user = loginController.getUser();
        this.setKweets(kweetService.getKweetsByUser(this.user));
    }

    public User getUser() {
        return user;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweets) {
        this.kweets = kweets;
        Collections.sort(kweets, new Comparator<Kweet>() {
            public int compare(Kweet o1, Kweet o2) {
                return o1.getPostDate().compareTo(o2.getPostDate());
            }
        });
    }

    public List<User> getFollowing() {
        return following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    private void findAllFollowing() {
        if (!user.getFollowers().isEmpty()) {
            for (User user : following) {
                kweets.addAll(user.getKweets());
            }
        }
    }

    public String getNumbersOfFollowers() {
        return String.valueOf(this.user.getFollowers().size());
    }

    public String getNumbersOfFollowing() {
        //TODO: Get following function
        return String.valueOf(this.user.getFollowers().size());
    }

    public String getNumberOfkweets() {
        return String.valueOf(this.user.getKweets().size());
    }

//    private void findAllFollowers() {
//        followers = (List<User>) kweetService.findAllFollower(user.getUserID());
//    }

//    public void searchKweet(AjaxBehaviorEvent event) {
//        UIInput input = (UIInput) event.getSource();
//        String searchString = (String) input.getValue();
//        this.setKweets((List<Kweet>) kweetService.findAllKweetsBasedOnText(searchString));
//    }

}