package org.springframework.context;

import org.springframework.beans.BeansException;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.Closeable;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public interface ConfigurableApplicationContext extends ApplicationContext,  Lifecycle, Closeable{

    String CONFIG_LOCATION_DELIMITERS = ",; \t\n";

    String CONVERSION_SERVICE_BEAN_NAME = "conversionService";

    String LOAD_TIME_WEAVER_BEAN_NAME = "loadTimeWeaver";

    String ENVIRONMENT_BEAN_NAME = "environment";

    String SYSTEM_PROPERTIES_BEAN_NAME = "systemProperties";

    String SYSTEM_ENVIRONMENT_BEAN_NAME = "systemEnvironment";

    void setId(String id);

    void setParent(ApplicationContext parent);

    void setEnvironment(ConfigurableEnvironment environment);

    @Override
    ConfigurableEnvironment getEnvironment();

    @Override
    void close();

    boolean isActive();

    void refresh() throws BeansException, IllegalStateException;
}
