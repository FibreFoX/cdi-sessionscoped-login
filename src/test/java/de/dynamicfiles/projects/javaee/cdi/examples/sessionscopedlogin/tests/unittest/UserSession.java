package de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.tests.unittest;

import de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.model.User;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/**
 *
 * @author FibreFoX
 */
public class UserSession {

    @Test
    public void create() {
        AssertJUnit.assertNotNull(new de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.UserSession());
    }

    @Test(dependsOnMethods = "create")
    public void nullUserInFreshInstance() {
        AssertJUnit.assertNull(new de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.UserSession().getUser());
    }

    @Test(dependsOnMethods = "create")
    public void getUser() {
        de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.UserSession session = new de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.UserSession();
        User user = new User();
        session.setUser(user);
        AssertJUnit.assertSame(user, session.getUser());
    }
}
