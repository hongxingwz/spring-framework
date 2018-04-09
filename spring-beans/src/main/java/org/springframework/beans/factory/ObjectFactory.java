package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
