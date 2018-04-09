package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
