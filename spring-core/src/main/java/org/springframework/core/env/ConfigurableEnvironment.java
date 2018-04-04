package org.springframework.core.env;

import java.util.Map;

/**
 * @author jianglei
 * @since 2018/4/4
 */
public interface ConfigurableEnvironment extends Environment, ConfigurablePropertyResolver {

    void setActiveProfiles(String... profiles);

    void addActiveProfile(String profile);

    void setDefaultProfiles(String... profiles);

    MutablePropertySources getPropertySources();

    Map<String, Object> getSystemEnvironment();

    Map<String, Object> getSystemProperties();

    void merge(ConfigurableEnvironment parent);
}
