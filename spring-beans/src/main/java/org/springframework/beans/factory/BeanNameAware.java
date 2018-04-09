package org.springframework.beans.factory;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public interface BeanNameAware extends Aware{

    void setBeanName(String name);
}
