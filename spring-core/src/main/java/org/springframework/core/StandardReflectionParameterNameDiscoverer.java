package org.springframework.core;

import org.springframework.lang.UsesJava8;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 使用JDK8的反射机制的{@link ParameterNameDiscoverer}的实现，用于获取
 * 参数名称(基于"-parameters"编译标识)
 *
 * @author jianglei
 * @since 2018/4/8
 * @see java.lang.reflect.Method#getParameters()
 * @see java.lang.reflect.Method#getName()
 */
@UsesJava8
public class StandardReflectionParameterNameDiscoverer implements ParameterNameDiscoverer {
    @Override
    public String[] getParameterNames(Method method) {
        Parameter[] parameters = method.getParameters();
        return getParameterNames(parameters);
    }

    @Override
    public String[] getParameterNames(Constructor<?> ctor) {
        Parameter[] parameters = ctor.getParameters();
        return getParameterNames(parameters);
    }

    private String[] getParameterNames(Parameter... parameters) {
        String[] parameterNames = new String[parameters.length];
        for(int i = 0; i < parameterNames.length; i++) {
            Parameter param = parameters[i];
            if (!param.isNamePresent()) {
                return null;
            }
            parameterNames[i] = param.getName();
        }
        return parameterNames;
    }
}
