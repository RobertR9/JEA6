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
    private User user;

    public StartPageController() {
    }

    @PostConstruct
    public void init() {
        System.err.println("Init \n");
        System.err.println(loginController.getUser());
        this.user = loginController.getUser();
        trends = new ArrayList<>();
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

//    public void searchKweet(AjaxBehaviorEvent event) {
//        UIInput input = (UIInput) event.getSource();
//        String searchString = (String) input.getValue();
//        this.setKweets((List<Kweet>) kweetService.findAllKweetsBasedOnText(searchString));
//    }

    public void createKweet() {
        kweetService.add(this.newKweet, loginController.getUser());
        this.newKweet = "";
    }

    public String getLatestKweet() {
        List<Kweet> kweets = kweetService.getKweetsByUser(loginController.getUser());
        if (kweets.size() != 0) {
            Kweet kweet = kweets.get(0);
            return kweet.getPostDate() + ": " + kweet.getKweet();
        }
        return "You havent posted a kweet yet.";
    }

    public List<Kweet> getTimeLine() {
        List<Kweet> kweets = kweetService.getKweetsForUser(loginController.getUser());
        System.err.print("Timeling size: " + kweets.size());
        for (Kweet kweet :
                kweets) {
            System.err.print("Kweet: " +kweet.getId());
        }
        return kweets;
    }
}