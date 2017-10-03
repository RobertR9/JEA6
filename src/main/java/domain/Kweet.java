package domain;

import javax.persistence.*;
import java.util.Date;

@Entity()
@NamedQueries({
        @NamedQuery(name = "Kweet.findAllByUser", query = "SELECT k FROM Kweet as k WHERE k.owner = :user ORDER BY k.postDate desc"),
        @NamedQuery(name = "Kweet.findBySearchString", query = "SELECT k FROM Kweet as k WHERE k.kweet LIKE :searchString"),
        @NamedQuery(name = "Kweet.findMentions", query = "SELECT k FROM Kweet as k WHERE k.kweet LIKE :username"),

})
public class Kweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 140)
    private String kweet;
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
        this.kweet = tweet;
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

    public String getKweet() {
        return kweet;
    }

    public Kweet setTweet(String kweet) {
        this.kweet = kweet;
        return this;
    }

    public User getOwner() {
        return owner;
    }


    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
    //endregion
}
