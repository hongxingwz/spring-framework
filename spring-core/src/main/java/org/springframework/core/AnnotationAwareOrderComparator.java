package org.springframework.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public class AnnotationAwareOrderComparator extends OrderComparator {

    public static final AnnotationAwareOrderComparator INSTANCE = new AnnotationAwareOrderComparator();




    public static void sort(List<?> list) {
        if (list.size() > 1) {
            Collections.sort(list, INSTANCE);
        }
    }

    public static void sort(Object[] array) {
        if (array.length > 1) {
            Arrays.sort(array, INSTANCE);
        }
    }

    public static void sortIfNecessary(Object value) {
        if (value instanceof Object[]) {
            sort((Object[]) value);
        } else if (value instanceof List) {
            sort((List<?>) value);
        }
    }
}
