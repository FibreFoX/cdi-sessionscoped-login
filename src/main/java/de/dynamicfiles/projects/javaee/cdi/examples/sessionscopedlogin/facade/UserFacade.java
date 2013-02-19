package de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.facade;

import de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.model.User;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * cdi facade without usage of ejb, so the transactions are
 * bean-managed-transactions (BMT)
 *
 * @author FibreFoX
 */
@ApplicationScoped
public class UserFacade implements Serializable {

    private static final long serialVersionUID = 1L;
    @PersistenceContext(unitName = "sessionscopedlogin_PU")
    private transient EntityManager entityManager;

    /**
     * Searches for an user with given username/password and returns it,
     * otherwise returns null.
     *
     * @param username
     * @param password
     * @return
     */
    public User getUser(String username, String password) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> foundUsers = query.getResultList();
        if (foundUsers != null && !foundUsers.isEmpty()) {
            return foundUsers.get(0);
        }
        return null;
    }

    /**
     * simply stores the given user into the database
     *
     * @param user
     */
    public void storeUser(User user) {
        entityManager.persist(user);
    }

    /**
     * returns all stored users, but without criteria-builder.
     *
     * @return
     */
    public List<User> getAll() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }
}