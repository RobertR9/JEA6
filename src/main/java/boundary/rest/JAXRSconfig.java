/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class JAXRSconfig extends Application {
    private final Set<Class<?>> classes;

    public JAXRSconfig() {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(UserResource.class);
        c.add(KweetResource.class);
        this.classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

}
