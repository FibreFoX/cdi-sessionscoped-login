package de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.interceptor;

import de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin.annotation.Transactional;
import java.io.Serializable;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.UserTransaction;

/**
 * CDI-interceptor (which has to be enabled in web.xml) for handling
 * transactions (which are BMT ones).
 *
 * @author FibreFoX
 */
@Transactional
@Interceptor
public class TransactionInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private transient UserTransaction utx;

    /**
     * If any exception occurs while process, the complete transaction will be
     * rolled back.
     *
     * @param ic
     * @return
     */
    @AroundInvoke
    public Object applyTransaction(InvocationContext ic) {
        Object result = null;
        try {
            utx.begin();
            result = ic.proceed();
            utx.commit();
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception e2) {
            }
        }
        return result;
    }
}
