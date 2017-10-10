package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity(name = "Users")
@NamedQueries({
        @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM Users as u WHERE u.username = :username"),
        @NamedQuery(name = "User.findById", query = "SELECT u FROM Users as u WHERE u.id = :id"),
        @NamedQuery(name = "User.findFollowers", query = "SELECT u.followers FROM Users as u WHERE u = :user"),
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM Users u")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    //Profile settings
    private String name;
    private String location;
    private String web;
    private String bio;
    private String profilePicture;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<User> followers = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private Collection<Kweet> kweets = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userID"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleID"))
//    private Collection<Role> roles = new ArrayList();

    /**
     * Constructors
     */
    public User() {
        //Empty constructor
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
    }

    /**
     * @param id       Integer
     * @param username String
     * @param password String
     * @param name     String
     * @param location String
     * @param web      String
     * @param bio      String
     */
    public User(Integer id, String username, String password, String name, String location, String web, String bio) {
        this.id = new Long(id);
        this.username = username;
        this.password = password;
        this.name = name;
        this.location = location;
        this.web = web;
        this.bio = bio;
    }


    //region Getters and Setters

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
    //endregion

    public boolean addFollower(User follower) {
        return followers.add(follower);
    }

    public boolean deleteFollower(User follower) {
        return followers.remove(follower);
    }

    public Collection<User> getFollowers() {
        return followers;
    }

    public Kweet addKweet(String message) {
        if (message == null || message.isEmpty()) {
            return null;
        }
        Kweet kweet = new Kweet(message, new Date(), this);
        this.kweets.add(new Kweet(message, new Date(), this));
        return kweet;
    }

    public Collection<Kweet> getKweets() {
        return kweets;
    }

    public void addKweet(Kweet kweet) {
        this.kweets.add(kweet);
    }

    public void removeKweet(Kweet kweet) {
        kweets.removeIf(kwt -> kwt.getKweet().equals(kweet.getKweet()));
    }
}