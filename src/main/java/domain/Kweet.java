package domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Kweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 140)
    private String tweet;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date postDate;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User owner;

    /**
     * Constructors
     */
    public Kweet() {
    }

    /**
     * @param tweet    String
     * @param postDate Date
     * @param owner    User
     */
    public Kweet(String tweet, Date postDate, User owner) {
        this.tweet = tweet;
        this.postDate = postDate;
        this.owner = owner;
    }

    //region Getters & Setters
    /**
     * Getters & Setters
     */
    public Long getId() {
        return id;
    }

    public String getTweet() {
        return tweet;
    }

    public Kweet setTweet(String tweet) {
        this.tweet = tweet;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Kweet setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
    //endregion
}
