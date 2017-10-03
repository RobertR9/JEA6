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

    public User findById(Long id) {
        return this.userDAOJPAImpl.find(id);
    }

    public User findByUsername(String username) {
        return this.userDAOJPAImpl.find(username);
    }

    public void setUserDAO(UserDAOJPAImpl userDAOJPAImpl) {
        this.userDAOJPAImpl = userDAOJPAImpl;
    }

    public User login(String username, String password) {
        User found = this.userDAOJPAImpl.find(username);
        if (found == null) {
            return null;
        } else if (found.getPassword().equals(password)) {
            return found;
        }
        return null;
    }
}
