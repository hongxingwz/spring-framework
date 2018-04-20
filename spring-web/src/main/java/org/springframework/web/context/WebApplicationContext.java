package org.springframework.context;

import javax.servlet.ServletContext;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public interface WebApplicationContext extends ApplicationContext {

    String ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE = WebApplicationContext.class.getName() + ".ROOT";

    String SCOPE_REQUEST = "request";

    String SCOPE_SESSION = "session";

    String SCOPE_GLOBAL_SESSION = "globalSession";

    String SCOPE_APPLICATION = "application";

    String SERVLET_CONTEXT_BEAN_NAME = "servletContext";

    String CONTEXT_PARAMETERS_BEAN_NAME = "contextParameters";

    String CONTEXT_ATTRIBUTES_BEAN_NAME = "contextAttributes";

    ServletContext getServletContext();
}