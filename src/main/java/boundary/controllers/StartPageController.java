package boundary.controllers;

import domain.Kweet;
import domain.User;
import service.KweetService;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@ManagedBean
@SessionScoped
public class StartPageController implements Serializable {
    @Inject
    private KweetService kweetService;
    @Inject
    private UserService userService;
    @Inject
    private LoginController loginController;

    private String newKweet = "";
    private List<String> trends;
    private List<Kweet> kweets;
    private List<Kweet> mentions;
    private List<User> following;
    private List<User> followers;
    private User user;
    private Kweet kweet;
    private int sizeFollowing = 0;
    private int sizeFollowers = 0;

    public StartPageController() {
    }

    @PostConstruct
    public void init() {
        System.err.println("Init \n");
        System.err.println(loginController.getUser());
        this.user = loginController.getUser();
        kweets = kweetService.getKweetsByUser(this.user);
        trends = new ArrayList<>();
//        findAllFollowing();
//        sizeFollowing = following.size();
//        sizeFollowers = followers.size();
//        //Sorting kweets on asc postDate
//        kweets.sort((o1, o2) -> o2.getPostDate().compareTo(o1.getPostDate()));
//
//
    }

    public String getNewKweet() {
        return newKweet;
    }

    public void setNewKweet(String newKweet) {
        this.newKweet = newKweet;
    }

    public List<String> getTrends() {
//        //TODO: This is mocking data
        trends.add("#JSF");
        trends.add("#Trends");
        trends.add("#XHTML");
        return trends;
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

    public int getSizeFollowing() {
        return sizeFollowing;
    }

    public List<User> getFollowing() {
        return following;
    }

    public int getSizeFollowers() {
        return sizeFollowers;
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

//    private void findAllFollowers() {
//        followers = (List<User>) kweetService.findAllFollower(user.getUserID());
//    }

//    public void searchKweet(AjaxBehaviorEvent event) {
//        UIInput input = (UIInput) event.getSource();
//        String searchString = (String) input.getValue();
//        this.setKweets((List<Kweet>) kweetService.findAllKweetsBasedOnText(searchString));
//    }

    public void createKweet() {
        Kweet kweet = kweetService.add(this.newKweet, getUser());
        this.kweets.add(kweet);
        kweets.sort((o1, o2) -> o2.getPostDate().compareTo(o1.getPostDate()));
        this.newKweet = "";
    }

    public String getLatestKweet() {
        if (kweets.size() != 0) {
            Kweet kweet = this.kweets.get(0);
            return kweet.getPostDate() + ": " + kweet.getKweet();
        }
        return "You havent posted a kweet yet.";
    }
}