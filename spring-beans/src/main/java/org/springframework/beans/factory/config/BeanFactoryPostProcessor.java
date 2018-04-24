package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
