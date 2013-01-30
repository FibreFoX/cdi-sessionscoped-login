package de.dynamicfiles.projects.javaee.cdi.examples.sessionscopedlogin;

import java.io.Serializable;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.UserTransaction;

@Transactional
@Interceptor
public class TransactionInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private UserTransaction utx;

    @AroundInvoke
    public Object applyTransaction(InvocationContext ic) throws Throwable {
        utx.begin();
        Object result = ic.proceed();
        utx.commit();
        return result;
    }
}
