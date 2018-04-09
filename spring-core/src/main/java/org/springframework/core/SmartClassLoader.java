package org.springframework.core;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public interface SmartClassLoader {

    boolean isClassReloadable(Class<?> clazz);
}
