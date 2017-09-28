package service;

import dao.UserDAOJPAImpl;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class UserService implements Serializable {

    @Inject
    private UserDAOJPAImpl userDAOJPAImpl;

    public void add(User user) {
        userDAOJPAImpl.createUser(user);
    }

    public void edit(User user) {
        userDAOJPAImpl.editUser(user);
    }

    public void delete(User user) {
        userDAOJPAImpl.removeUser(user);
    }

    public List<User> getFollowers(User user) {
        return userDAOJPAImpl.findAllFollowers(user);
    }

    public List<User> getAllUsers() {
        return userDAOJPAImpl.findAllUsers();
    }
}
