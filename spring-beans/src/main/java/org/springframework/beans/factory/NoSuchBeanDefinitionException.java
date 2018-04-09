package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public class NoSuchBeanDefinitionException extends BeansException {

    public NoSuchBeanDefinitionException(String msg) {
        super(msg);
    }

    public NoSuchBeanDefinitionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
