package de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class UserFacade implements Serializable {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = "sessionscopedlogin_PU")
    private EntityManager entityManager;

    public User getUser(String username, String password) {
        TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> foundUsers = query.getResultList();
        if (foundUsers != null && !foundUsers.isEmpty()) {
            return foundUsers.get(0);
        }
        return null;
    }

    public void storeUser(User user) {
        getEntityManager().persist(user);
    }

    public List<User> getAll() {
        TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}