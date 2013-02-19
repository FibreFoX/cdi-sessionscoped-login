package de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.tests.integration;

import de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.qualifier.Current;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;

/**
 * producer class that provides us with an testable instance for
 * integration-test with tomEE
 *
 * @author FibreFoX
 */
public class TestUserSessionproducer {

    /*
     * Creates a new @Dependent-scoped UserSession CDI-Bean with "Current"-qualifier
     */
    @Produces
    @Current
    public de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.UserSession getUserSession(@New de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.UserSession userSession) {
        return userSession;
    }
}
