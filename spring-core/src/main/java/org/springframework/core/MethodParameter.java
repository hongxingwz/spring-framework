package org.springframework.core;

import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author jianglei
 * @since 2018/4/20
 */
public class MethodParameter {

    private static final Annotation[] EMPTY_ANNOTATION_ARRAY = new Annotation[0];

    private static final Class<?> javaUtilOptionalClass;

    static {
        Class<?> clazz;
        try {
            clazz = ClassUtils.forName("Java.util.Optional", MethodParameter.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            clazz = null;
        }
        javaUtilOptionalClass = clazz;
    }

    private final Method method;

    private final Constructor<?> constructor;

    private final int parameterIndex;

    private int nestingLevel = 1;

    Map<Integer, Integer> typeIndexesPerLevel;

    private volatile Class<?> containingClass;

    private volatile Class<?> parameterType;

    private volatile Type genericParameterType;

    private volatile Annotation[] parameterAnnotations;

    private volatile ParameterNameDiscoverer parameterNameDiscoverer;

    private volatile String parameterName;

    private volatile MethodParameter nestedMethodParameter;





}
