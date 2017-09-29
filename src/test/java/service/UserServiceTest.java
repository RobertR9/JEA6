package service;

import dao.UserDAOJPAImpl;
import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserDAOJPAImpl userDAO;

    @Before
    public void setUp() {
        userService = new UserService();
        userService.setUserDAO(userDAO);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void savingUserSuccessful() {
        User user = new User("Henk", "test", "Henk de Testman", "The Netherlands", "www.google.nl", "Houd van testen");
        userService.add(user);
        verify(userDAO, Mockito.times(1)).createUser(user);
    }

    @Test
    public void findUserSuccessfull() {
        User user = new User("Henk", "test", "Henk de Testman", "The Netherlands", "www.google.nl", "Houd van testen");

        when(userService.findByUsername(user.getUsername())).thenReturn(user);
        User found = userService.findByUsername(user.getUsername());
        assertThat(found, is(user));
    }
}
