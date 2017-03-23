package domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tweet {
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
    public Tweet() {
    }

    /**
     * @param tweet    String
     * @param postDate Date
     * @param owner    User
     */
    public Tweet(String tweet, Date postDate, User owner) {
        this.tweet = tweet;
        this.postDate = postDate;
        this.owner = owner;
    }

    /**
     * Getters & Setters
     */
    public Long getId() {
        return id;
    }

    public String getTweet() {
        return tweet;
    }

    public Tweet setTweet(String tweet) {
        this.tweet = tweet;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Tweet setOwner(User owner) {
        this.owner = owner;
        return this;
    }
}
