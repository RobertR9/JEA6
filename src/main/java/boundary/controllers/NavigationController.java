package boundary.controllers;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("NavigationBean")
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
     * Redirect to overview page.
     *
     * @return Overview page name.
     */
    public String redirectToOverview() {
        return "/secured/overview.xhtml?faces-redirect=true";
    }

    /**
     * Go to overview page.
     *
     * @return Overview page name.
     */
    public String toOverview() {
        return "/secured/overview.xhtml";
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
