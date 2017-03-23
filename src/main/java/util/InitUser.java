package util;

import dao.UserDAO;
import domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class InitUser {

    @Inject
    UserDAO ud;

    @PostConstruct
    public void init() {
        User hans = new User("Hans", "hans", "hans test", "eindhoven", "www.google.com", "zwemmen");
        User frank = new User("Frank", "frank", "frank test", "den bosch", "www.google.com", "fietsen");
        User bert = new User("Bert", "bert", "bert test", "nijmegen", "www.google.com", "vissen");
        User klaas = new User("Klaas", "klaas", "klaas test", "tiel", "www.google.com", "voetballen");
        User jan = new User("Jan", "jan", "jan test", "den haag", "www.google.com", "gamen");

        hans.addFollowing(frank);
        hans.addFollowing(bert);
        hans.addFollowing(klaas);
        hans.addFollowing(jan);

        frank.addFollowing(bert);
        frank.addFollowing(klaas);
        frank.addFollowing(jan);

        bert.addFollowing(klaas);

        jan.addFollowing(hans);

        bert.addTweet("First tweet from " + bert.getUsername());
        hans.addTweet("First tweet from " + hans.getUsername());
        frank.addTweet("First tweet from " + frank.getUsername());
        klaas.addTweet("First tweet from " + klaas.getUsername());
        jan.addTweet("First tweet from " + jan.getUsername());

        ud.save(hans);
        ud.save(frank);
        ud.save(bert);
        ud.save(klaas);
        ud.save(jan);
    }

}
