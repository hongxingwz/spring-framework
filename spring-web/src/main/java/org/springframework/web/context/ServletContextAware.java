package org.springframework.web.context;

import org.springframework.beans.factory.Aware;

import javax.servlet.ServletContext;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public interface ServletContextAware extends Aware {

    void setServletContext(ServletContext servletContext);
}
