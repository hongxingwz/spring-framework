package org.springframework.web.context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public abstract class AbstractContextLoaderInitializer implements WebApplicationInitializer {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        registerContextLoaderListener(servletContext);
    }

    protected void registerContextLoaderListener(ServletContext servletContext) {
        WebApplicationContext rootAppContext = createRootApplicationContext();
        if (rootAppContext != null) {

        } else {
            logger.debug("No ContextLoaderListener registered, as " +
                "createRootApplicationContext() did not return an application context");
        }
    }

    protected abstract WebApplicationContext createRootApplicationContext();

    protected ApplicationContextInitializer<?>[] getRootApplicationContextInitializers() {
        return null;
    }
}
