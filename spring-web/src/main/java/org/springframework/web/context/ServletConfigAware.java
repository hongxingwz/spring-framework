package org.springframework.web.context;

import org.springframework.beans.factory.Aware;

import javax.servlet.ServletConfig;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public interface ServletConfigAware extends Aware {

    void setServletConfig(ServletConfig servletConfig);
}
