package org.springframework.beans.factory;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
