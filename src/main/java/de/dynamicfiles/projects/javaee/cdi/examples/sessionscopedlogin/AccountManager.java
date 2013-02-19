package de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin;

import de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.model.User;
import de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.facade.UserFacade;
import de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.annotation.Transactional;
import de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.qualifier.Current;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * with the accountmanager it is possible to access the facade and session
 * without having them in the faces-scopes, just to have this beans seperated
 * (may lead to inconsistence and other "unlogical" errors).
 *
 * @author FibreFoX
 */
public class AccountManager implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private UserFacade userFacade;
    @Inject
    @Current
    private UserSession userSession;
    @Inject
    private Credentials credentials;

    /**
     * returns the current in usersession stored user, may be null
     *
     * @return
     */
    public User getUser() {
        return userSession.getUser();
    }

    /**
     * tries to "login" the user by given credentials
     *
     * @return
     */
    public String login() {
        userSession.setUser(userFacade.getUser(credentials.getUsername(), credentials.getPassword()));
        return "index.xhtml";
    }

    /**
     * destroyes the instance and redirects to startpage
     *
     * @return
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }

    /**
     * Creates a user with given credentials, generates some
     * first-/lastname-fields and stores that user. The transaction is
     * controlled by "Transactional"-annotation, so we KNOW when the transaction
     * is comitted. With SLSB ejb the container decides WHEN to commit, but in
     * usecases we have to do more than ONE ".persist" i find it hard to have
     * clear states.
     *
     * @return admin-view
     */
    @Transactional
    public String createUser() {
        User user = new User();
        user.setUsername(credentials.getUsername());
        user.setPassword(credentials.getPassword());

        user.setFirstname("firstname-" + credentials.getUsername());
        user.setLastname("lastname-" + credentials.getUsername());
        userFacade.storeUser(user);
        return "admin.xhtml";
    }

    /**
     * returns all stored user, if nothing found, returns a empty list.
     *
     * @return
     */
    public List<User> getAllUser() {
        List<User> foundUser = userFacade.getAll();
        if (foundUser != null && !foundUser.isEmpty()) {
            return foundUser;
        }
        return new ArrayList();
    }
}