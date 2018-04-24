package org.springframework.web.context;

import org.springframework.core.env.ConfigurableEnvironment;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * @author jianglei
 * @since 2018/4/20
 */
public interface ConfigurableWebEnvironment  extends ConfigurableEnvironment{

    void initPropertySources(ServletContext servletContext, ServletConfig servletConfig);
}
