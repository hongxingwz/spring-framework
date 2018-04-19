package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public abstract class AnnotationUtils {

    public static final String VALUE = "value";

    private static final String REPEATABLE_CLASS_NAME = "java.lang.annotation.Repeatable";


    public static <A extends Annotation> A getAnnotation(AnnotatedElement annotatedElement, Class<A> annotationType) {

        return null;
    }

}
