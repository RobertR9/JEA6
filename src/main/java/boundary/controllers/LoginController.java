package boundary.controllers;

import domain.User;
import org.eclipse.persistence.jpa.jpql.Assert;
import service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("LoginController")
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 4L;
    private transient UserService userService;
    private NavigationController navigationController;
    private String username;
    private String password;
    private User user;
    private boolean loggedIn = false;

    public LoginController() {
        //Empty constructor
    }

    @Inject
    public LoginController(UserService userService, NavigationController navigationController) {
        Assert.isNotNull(userService, "Userservice cannot be null.");
        Assert.isNotNull(navigationController, "NavigationBean cannot be null.");
        this.userService = userService;
        this.navigationController = navigationController;
    }

    /**
     * Login operation.
     *
     * @return url String
     */
    public String doLogin() {
        if (userService.login(this.username, this.password) == null) {
            //Set login ERROR
            FacesMessage msg = new FacesMessage("Invalid credentials.", "ERROR MSG");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage("login-form", msg);

            //To to login page
            return navigationController.redirectToLogin();
        }
        this.setUser(userService.login(this.username, this.password));
        this.setLoggedIn(true);
        return navigationController.redirectToIndex();
    }

    /**
     * Logout operation.
     *
     * @return url String
     */
    public String doLogout() {
        System.err.print("logout");
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;

        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "/login.xhtml?faces-redirect=true";
//        return navigationController.redirectToLogin();
    }

    // ------------------------------
    // Getters & Setters

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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    private void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @SuppressWarnings("unused")
    public NavigationController getNavigationController() {
        return navigationController;
    }
}
