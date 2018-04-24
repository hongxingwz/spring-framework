package org.springframework.beans;

import org.springframework.beans.factory.FatalBeanException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 当初始化一个bean失败时抛出的异常。
 * 携带bean class
 * @author jianglei
 * @since 2018/4/20
 */
public class BeanInstantiationException extends FatalBeanException {

    private Class<?> beanClass;

    private Constructor<?> constructor;

    private Method constructingMethod;

    public BeanInstantiationException(Class<?> beanClass, String msg) {
        this(beanClass, msg, null);
    }

    public BeanInstantiationException(Class<?> beanClass, String msg, Throwable cause) {
        super("Failed to instantiate [" + beanClass.getName() + "]: " + msg, cause);
        this.beanClass = beanClass;
    }

    public BeanInstantiationException(Constructor<?> constructor, String msg, Throwable cause) {
        super("Failed to instantiate [" + constructor.getDeclaringClass().getName() + "]: " + msg, cause);
        this.beanClass = constructor.getDeclaringClass();
        this.constructor = constructor;
    }

    public BeanInstantiationException(Method constructingMethod, String msg, Throwable cause) {
        super("Failed to instantiate [" + constructingMethod.getReturnType().getName() + "]: " + msg, cause);
        this.beanClass = constructingMethod.getReturnType();
        this.constructingMethod = constructingMethod;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public Constructor<?> getConstructor() {
        return constructor;
    }

    public Method getConstructingMethod() {
        return constructingMethod;
    }
}
