package domain;

import javax.persistence.*;
import java.util.Date;

@Entity()
@NamedQueries({
        @NamedQuery(name = "Kweet.findAllByUser", query = "SELECT k FROM Kweet as k WHERE k.owner = :user ORDER BY k.postDate DESC"),
        @NamedQuery(name = "Kweet.findBySearchString", query = "SELECT k FROM Kweet as k WHERE k.message LIKE :searchString"),
        @NamedQuery(name = "Kweet.findMentions", query = "SELECT k FROM Kweet as k WHERE k.message LIKE :username"),
        @NamedQuery(name = "Kweet.findTrends", query = "SELECT k FROM Kweet as k WHERE k.message LIKE :searchString"),

})
public class Kweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 140)
    private String message;
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
        this.message = tweet;
        this.postDate = postDate;
        this.owner = owner;
    }

    /**
     * @param id       Integer
     * @param message    String
     * @param postDate Date
     * @param owner    User
     */
    public Kweet(Integer id, String message, Date postDate, User owner) {
        this.id = new Long(id);
        this.message = message;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setOwner(User owner) {

        this.owner = owner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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


    @Override
    public String toString() {
        return "Kweet{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", postDate=" + postDate +
                ", owner=" + owner.getId() +
                '}';
    }
}
