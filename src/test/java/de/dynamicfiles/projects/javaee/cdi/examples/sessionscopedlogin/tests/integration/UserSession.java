package de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.tests.integration;

import de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.qualifier.Current;
import java.io.File;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class UserSession extends Arquillian {

    @Inject
    @Current
    de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.UserSession userSession;

    @Deployment
    public static WebArchive deploy() {
        return ShrinkWrap.create(WebArchive.class, "UserSession-test.war")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml") // for having cdi
                .addAsManifestResource(new File("src/main/resources/META-INF", "persistence.xml"), "persistence.xml") // for having jpa
                .addClass(de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.UserSession.class)
                .addClass(de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.qualifier.Current.class)
                .addClass(TestUserSessionproducer.class) // add our own UserSession-producer to avoid testing session-/requestscoped beans
                .addClass(de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.model.User.class);
    }

    /**
     * Just check if CDI bean was injected and is accessable.
     */
    @Test
    public void sessionExists() {
        AssertJUnit.assertNotNull(userSession);
        AssertJUnit.assertNull(userSession.getUser()); // would be throw an Exception if proxy can't access the real instance
    }
}
