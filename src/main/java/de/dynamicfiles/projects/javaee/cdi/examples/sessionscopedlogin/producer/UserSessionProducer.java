package de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.producer;

import de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.UserSession;
import de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.qualifier.Current;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;

/**
 *
 * @author FibreFoX
 */
public class UserSessionProducer {

    @Produces
    @SessionScoped
    @Current
    public UserSession getUserSession(@New UserSession userSession) {
        return userSession;
    }
}
