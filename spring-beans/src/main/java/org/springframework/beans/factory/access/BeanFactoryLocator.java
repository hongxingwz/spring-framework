package org.springframework.beans.factory.access;

import org.springframework.beans.BeansException;

/**
 * @author jianglei
 * @since 2018/4/20
 */
public interface BeanFactoryLocator {

    BeanFactoryReference useBeanFactory(String factoryKey) throws BeansException;
}
