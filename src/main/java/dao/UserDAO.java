package dao;

import domain.User;

import java.util.List;

public interface UserDAO {

    /**
     * Method to create users for testing purposes.
     */
    void initUsers();

    /**
     * @param user User
     * @return User
     */
    User createUser(User user);

    /**
     * @param user User
     * @return User
     */
    User editUser(User user);

    /**
     * @param user User
     */
    void removeUser(User user);

    /**
     * @param id Long
     * @return User
     */
    User find(Long id);

    /**
     * @param username String
     * @return User
     */
    User find(String username);

    /**
     * @return List<User>
     */
    List<User> findAllUsers();

    /**
     * Returns a list of Users which the given user is following.
     *
     * @param user User
     * @return List<User>
     */
    List<User> findAllFollowers(User user);

    /**
     * @param user     User
     * @param follower User
     */
    void addFollower(User user, User follower);

    /**
     * @param user     User
     * @param follower User
     */
    void removeFollower(User user, User follower);


}