package org.springframework.core.env;

/**
 * 用于解析下面源属性的接口
 * @author jianglei
 * @since 2018/4/3
 */
public interface PropertyResolver {

    /**
     * 返回给定的属性键是否可以解析
     */
    boolean containsProperty(String key);

    /**
     * 返回给定键相关联的属性值，
     * 或{@code null}如果键不能被解析
     * @param key 要被解析的属性名
     * @see #getProperty(String, String)
     * @see #getProperty(String, Class)
     * @see #getRequiredProperty(String)
     */
    String getProperty(String key);

    String getProperty(String key, String defaultValue);

    <T> T getProperty(String key, Class<T> targetType);

    <T> T getProperty(String key, Class<T> targetType, T defaultValue);

    String getRequiredProperty(String key) throws IllegalStateException;

    <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException;

    String resolvePlaceholders(String text);

    String resolveRequiredPlaceholders(String text) throws IllegalArgumentException;
}
