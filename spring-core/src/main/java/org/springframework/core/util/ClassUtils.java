package org.springframework.core.util;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public abstract class ClassUtils {

    public static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            forName(className, classLoader);
            return true;
        } catch (Throwable ex) {
            return false;
        }
    }

    public static Class<?> forName(String name, ClassLoader classLoader) throws ClassNotFoundException, LinkageError {
        // TODO: 2018/4/9
        return null;
    }
}
