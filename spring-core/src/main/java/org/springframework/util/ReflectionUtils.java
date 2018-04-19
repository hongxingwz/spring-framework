package org.springframework.util;

import java.lang.reflect.Constructor;
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

    /**
     * 用给定的类和参数获取一个可访问的构造器
     * @param clazz 要检测的类
     * @param parameterTypes 需要的构造器的参数类型
     * @return 构造器引用
     * @throws NoSuchMethodException 如果没有这样的构造器存在
     */
    public static <T> Constructor<T> accessibleConstructor(Class<T> clazz, Class<?>... parameterTypes)
            throws NoSuchMethodException {
        Constructor<T> ctor = clazz.getDeclaredConstructor(parameterTypes);
        makeAccessible(ctor);
        return ctor;
    }

    public static void makeAccessible(Constructor<?> ctor) {
        if ((!Modifier.isPublic(ctor.getModifiers()) ||
                !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && !ctor.isAccessible()) {
            ctor.setAccessible(true);
        }
    }
}
