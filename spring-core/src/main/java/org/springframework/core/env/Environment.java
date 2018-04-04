package org.springframework.core.env;

/**
 * @author jianglei
 * @since 2018/4/3
 */
public interface Environment extends PropertyResolver {

    String[] getActiveProfiles();

    String[] getDefaultProfiles();

    boolean acceptsProfiles(String... profiles);
}
