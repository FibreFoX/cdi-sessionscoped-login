package de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AccountManager implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private UserFacade userFacade;
    @Inject
    private UserSession userSession;
    @Inject
    private Credentials credentials;

    public User getUser() {
        return userSession.getUser();
    }

    @Transactional
    public String login() {
        userSession.setUser(userFacade.getUser(credentials.getUsername(), credentials.getPassword()));
        return "index.xhtml";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }

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

    public List<User> getAllUser() {
        List<User> foundUser = userFacade.getAll();
        if (foundUser != null && !foundUser.isEmpty()) {
            return foundUser;
        }
        return new ArrayList();
    }
}