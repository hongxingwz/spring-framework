package org.springframework.beans.factory.access;

import org.springframework.beans.factory.BeanFactory;

/**
 * @author jianglei
 * @since 2018/4/20
 */
public interface BeanFactoryReference {

    BeanFactory getFactory();

    void release();
}
