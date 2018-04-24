package org.springframework.beans.factory.config;

import org.springframework.beans.factory.BeanFactory;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    int AUTOWIRE_NO = 0;

    int AUTOWIRE_BY_NAME = 1;

    int AUTOWIRE_BY_TYPE = 2;

    int AUTOWIRE_CONSTRUCTOR = 3;

    int AUTOWIRE_AUTODETECT = 4;


}
