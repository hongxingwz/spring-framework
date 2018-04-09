package org.springframework.beans.factory;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public interface SmartFactoryBean<T> extends FactoryBean<T> {

    boolean isPrototype();

    boolean isEagerInit();
}
