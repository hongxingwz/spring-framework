package org.springframework.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public interface WebApplicationInitializer {

    void onStartup(ServletContext servletContext) throws ServletException;
}
