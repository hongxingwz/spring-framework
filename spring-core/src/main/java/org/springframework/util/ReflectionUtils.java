package org.springframework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public abstract class ReflectionUtils {

    private static final String CGLIB_RENAMED_METHOD_PREFIX = "CGLIB$";

    private static final Method[] NO_METHODS = {};

    private static final Field[] NO_FIELDS = {};

    /**
     * 确定给定的字段是否是一个"public static final"常量
     * @param field 要检测的字段
     */
    public static boolean isPublicStaticFinal(Field field) {
        int modifiers = field.getModifiers();
        return (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers));
    }
}
