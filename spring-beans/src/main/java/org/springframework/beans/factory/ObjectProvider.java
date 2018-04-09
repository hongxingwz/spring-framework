package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public interface ObjectProvider<T> extends ObjectFactory<T> {

    T getObject(Object... args) throws BeansException;

    T getIfAvailable() throws BeansException;

    T getIfUnique() throws BeansException;
}
