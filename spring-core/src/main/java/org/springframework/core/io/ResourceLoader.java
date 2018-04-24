package org.springframework.core.io;

import org.springframework.util.ResourceUtils;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = ResourceUtils.CLASSPATH_URL_PREFIX;

    Resource getResource(String location);

    ClassLoader getClassLoader();

}
