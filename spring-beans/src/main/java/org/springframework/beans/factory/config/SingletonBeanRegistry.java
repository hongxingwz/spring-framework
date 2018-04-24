package org.springframework.beans.factory.config;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    String[] getSingletonNames();

    int getSingletonCount();

    Object getSingletonMutex();
}
