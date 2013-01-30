package de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

@SessionScoped
public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}