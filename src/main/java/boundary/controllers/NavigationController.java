package boundary.controllers;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("NavigationController")
@SessionScoped
public class NavigationController implements Serializable {

    private static final long serialVersionUID = 1520318172495977648L;

    /**
     * Redirect to login page.
     *
     * @return Login page name.
     */
    public String redirectToLogin() {
            return "/login.xhtml?faces-redirect=true";
    }

    /**
     * Go to login page.
     *
     * @return Login page name.
     */
    public String toLogin() {
        return "/login.xhtml";
    }

    /**
     * Redirect to welcome page.
     *
     * @return Welcome page name.
     */
    public String redirectToIndex() {
            return "/secured/index.xhtml?faces-redirect=true";
    }

    /**
     * Go to welcome page.
     *
     * @return Welcome page name.
     */
    public String toIndex() {
        return "/secured/index.xhtml";
    }

    /**
     * Redirect to profile page.
     *
     * @return Profile page name.
     */
    public String redirectToProfile() {
        return "/secured/profile.xhtml?faces-redirect=true";
    }

    /**
     * Go to profile page.
     *
     * @return Profile page name.
     */
    public String toProfile() {
        return "/secured/profile.xhtml";
    }

    /**
     * Redirect to account page.
     *
     * @return account page name.
     */
    public String redirectToAccount() {
        return "/secured/account.xhtml?faces-redirect=true";
    }

    /**
     * Go to account page.
     *
     * @return account page name.
     */
    public String toAccount() {
        return "/secured/account.xhtml";
    }
}
