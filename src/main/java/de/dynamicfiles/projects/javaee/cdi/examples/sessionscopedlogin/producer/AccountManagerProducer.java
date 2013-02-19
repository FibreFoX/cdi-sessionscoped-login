package de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.producer;

import de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.AccountManager;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * Producer for an AccountManager, that can be accessed from EL, only scoped for
 * the request.
 *
 * @author FibreFoX
 */
public class AccountManagerProducer {

    /**
     * Produces a NEW instance with requestscope, which can be accessed with
     * #{accountManager} in EL
     *
     * @param accountManager
     * @return
     */
    @Produces
    @Named("accountManager")
    @RequestScoped
    public AccountManager getAccountManager(@New AccountManager accountManager) {
        return accountManager;
    }
}
