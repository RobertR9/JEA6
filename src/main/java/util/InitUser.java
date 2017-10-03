package util;

import dao.UserDAOJPAImpl;
import domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class InitUser {

    @Inject
    UserDAOJPAImpl ud;

    @PostConstruct
    public void init() {
        System.out.println("INIT");
        User admin = new User("Admin", "admin", "Admin", "eindhoven", "www.google.com", "zwemmen");
        User hans = new User("Hans", "hans", "hans test", "eindhoven", "www.google.com", "zwemmen");
        User frank = new User("Frank", "frank", "frank test", "den bosch", "www.google.com", "fietsen");
        User bert = new User("Bert", "bert", "bert test", "nijmegen", "www.google.com", "vissen");
        User klaas = new User("Klaas", "klaas", "klaas test", "tiel", "www.google.com", "voetballen");
        User jan = new User("Jan", "jan", "jan test", "den haag", "www.google.com", "gamen");
        ud.createUser(admin);

        hans.addFollower(frank);
        hans.addFollower(bert);
        hans.addFollower(klaas);
        hans.addFollower(jan);

        frank.addFollower(bert);
        frank.addFollower(klaas);
        frank.addFollower(jan);

        bert.addFollower(klaas);

        jan.addFollower(hans);

        bert.addKweet("First tweet from " + bert.getUsername());
        hans.addKweet("First tweet from " + hans.getUsername());
        frank.addKweet("First tweet from " + frank.getUsername());
        klaas.addKweet("First tweet from " + klaas.getUsername());
        jan.addKweet("First tweet from " + jan.getUsername());

        ud.editUser(hans);
        ud.editUser(frank);
        ud.editUser(bert);
        ud.editUser(klaas);
        ud.editUser(jan);
        System.out.println("Initialization success.");
    }

}
