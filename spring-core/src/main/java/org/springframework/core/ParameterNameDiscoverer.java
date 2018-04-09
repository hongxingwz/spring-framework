package org.springframework.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 用于发现方法和构造器参数名的接口
 *
 * <p>参数名字的发现不会总是可能的，但是有多种策略可以用于尝试，
 * 比如可以寻找嵌入在编译时的debug信息，也可以寻找与之相关的注解信息。
 *
 * @author jianglei
 * @since 2018/4/8
 */
public interface ParameterNameDiscoverer {

    /**
     * 返回此方法的参数名，
     * 或者{@code null}如果确定不了的话。
     * @param method 用于寻找参数名的方法
     * @return 如果可以被解析的话，返回参数名数组，如果不能确定返回{@code null}
     */
    String[] getParameterNames(Method method);

    /**
     * 返回此构造器的参数名，
     * 如果确定不了的话返回{@code null}
     * @param ctor 用于寻找参数名的构造器
     * @return 如果可以被解析的话，返回参数名数组，如果不能确定返回{@code null}
     */
    String[] getParameterNames(Constructor<?> ctor);
}
