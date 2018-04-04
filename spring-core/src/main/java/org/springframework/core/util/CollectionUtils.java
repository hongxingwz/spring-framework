package org.springframework.core.util;

import java.util.Collection;
import java.util.Map;

/**
 * 混杂的集合实用方法。
 * 主要用于在框架内部使用。
 * @author jianglei
 * @since 2018/4/3
 */
public abstract class CollectionUtils {

    /**
     * 返回{@code true}如果提供的集合是{@code null}或者是空。
     * 否则，返回{@code false}。
     * @param collection 被检测的集合
     * @return 给定的集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 返回{@code true}如果提供的映射是{@code null}或者是空。
     * 否则，返回{@code false}。
     * @param map 要检测的映射
     * @return 给定的映射是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }
}
