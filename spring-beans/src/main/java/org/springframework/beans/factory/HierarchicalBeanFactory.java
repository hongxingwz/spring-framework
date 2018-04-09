package org.springframework.beans.factory;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public interface HierarchicalBeanFactory extends BeanFactory {

    BeanFactory getParentBeanFactory();

    boolean containsLocalBean(String name);
}
