package org.springframework.web.context;

import org.springframework.context.ConfigurableApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * @author jianglei
 * @since 2018/4/20
 */
public interface ConfigurableWebApplicationContext  extends WebApplicationContext, ConfigurableApplicationContext{

    String APPLICATION_CONTEXT_ID_PREFIX = WebApplicationContext.class.getName() + ":";

    String SERVLET_CONFIG_BEAN_NAME = "servletConfig";

    void setServletContext(ServletContext servletContext);

    void setServletConfig(ServletConfig servletConfig);

    ServletConfig getServletConfig();

    void setNamespace(String namespace);

    String getNamespace();

    void setConfigLocation(String configLocation);

    String[] getConfigLocations();
}
