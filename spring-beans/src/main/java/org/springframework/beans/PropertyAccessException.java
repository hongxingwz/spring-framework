package org.springframework.beans;

/**
 * @author jianglei
 * @since 2018/4/20
 */
@SuppressWarnings({"serial", "deprecation"})
public abstract class PropertyAccessException extends BeansException implements org.springframework.core.ErrorCoded{

    public PropertyAccessException(String msg) {
        super(msg);
    }
}
