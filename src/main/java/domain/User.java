package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity(name = "Users")
@NamedQueries({
        @NamedQuery(name = "User.getUserByUsername", query = "SELECT u FROM Users as u WHERE u.username = :username"),
        @NamedQuery(name = "User.getFollowersByUser", query = "SELECT u FROM Users as u WHERE u.following = :user"),
        @NamedQuery(name = "User.getTweetsByUser", query = "SELECT t FROM Tweet as t WHERE t.owner = :user"),
        @NamedQuery(name = "User.getAllUsers", query = "SELECT u FROM Users u")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String location;
    private String web;
    private String bio;
    private String profilePicture;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<User> following;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<User> followers;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private Collection<Tweet> tweets;

    /**
     * Constructors
     */
    public User() {
    }

    /**
     * @param username String
     * @param password String
     * @param name     String
     * @param location String
     * @param web      String
     * @param bio      String
     */
    public User(String username, String password, String name, String location, String web, String bio) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.location = location;
        this.web = web;
        this.bio = bio;

        // empty lists
        this.following = new ArrayList();
        this.followers = new ArrayList();
        this.tweets = new ArrayList();
    }

    /**
     * Getters & Setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Boolean addFollowing(User following) {
        // add as follower to following user
        following.addFollower(this);
        // add as following to this user
        return this.following.add(following);
    }

    public Boolean deleteFollowing(User following) {
        // remove as follower to following user
        following.deleteFollower(this);
        // remove as following to this user
        return this.following.remove(following);
    }

    public Collection<User> getFollowing() {
        return following;
    }

    public void setFollowing(Collection<User> following) {
        this.following = following;
    }

    private boolean addFollower(User follower) {
        return followers.add(follower);
    }

    private boolean deleteFollower(User follower) {
        return followers.remove(follower);
    }

    public Collection<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Collection<User> followers) {
        this.followers = followers;
    }

    public void addTweet(String message) {
        if (message == null || message.isEmpty()) {
            return;
        }

        this.tweets.add(new Tweet(message, new Date(), this));
    }

    public Collection<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(Collection<Tweet> tweets) {
        this.tweets = tweets;
    }
}