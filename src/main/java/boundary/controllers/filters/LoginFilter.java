package boundary.controllers.filters;

import boundary.controllers.LoginController;
import org.eclipse.persistence.jpa.jpql.Assert;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter("/secured/*")
public class LoginFilter implements Filter {
    private static final String LOGIN_PAGE = "/login.xhtml";
    private static final Logger LOGGER = Logger.getAnonymousLogger();
    private LoginController loginController;

    public LoginFilter() {
        //Empty Constructor
    }

    @Inject
    public LoginFilter(LoginController loginController) {
        Assert.isNotNull(loginController, "LoginController must not be null!");
        this.loginController = loginController;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.log(Level.INFO, "LoginFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // managed bean name is exactly the session attribute name

        if (loginController != null) {
            if (loginController.isLoggedIn()) {
                LOGGER.log(Level.INFO, "user is logged in");
                // user is logged in, continue request
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                LOGGER.log(Level.INFO, "user is not logged in");
                // user is not logged in, redirect to login page
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + LOGIN_PAGE);
            }
        } else {
            LOGGER.log(Level.WARNING, "LoginBean not found");
            // user is not logged in, redirect to login page
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + LOGIN_PAGE);
        }
    }

    @Override
    public void destroy() {
//        close resource
    }


}
