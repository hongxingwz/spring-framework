package org.springframework.beans.factory.config;

import org.springframework.beans.factory.ListableBeanFactory;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public interface ConfigurableListableBeanFactory
    extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory{

    void ignoreDependencyType(Class<?> type);

    void ignoreDependencyInterface(Class<?> ifc);



}
