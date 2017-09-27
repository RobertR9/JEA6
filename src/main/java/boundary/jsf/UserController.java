package boundary.jsf;

import domain.Kweet;
import domain.User;
import service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    @Inject
    private UserService userService;

    public UserController() {
    }

}
