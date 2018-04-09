package org.springframework.beans.factory;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public interface SmartInitializingSingleton {

    void afterSingletonsInstantiated();
}
