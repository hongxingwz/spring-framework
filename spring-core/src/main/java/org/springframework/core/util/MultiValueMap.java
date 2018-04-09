package org.springframework.core.util;

import java.util.List;
import java.util.Map;

/**
 * 拓展{@code Map}接口用于存储多个值。
 *
 * @author jianglei
 * @since 2018/4/9
 */
public interface MultiValueMap<K, V> extends Map<K, List<V>> {

    /**
     * 返回指定键的第一个值
     * @param key 键
     * @return 指定键的第一个值，或者{@code null}
     */
    V getFirst(K key);

    /**
     * 把给定的值添加到当前指定键的列表中
     * @param key 键
     * @param value 要被添加的值
     */
    void add(K key, V value);

    /**
     * 设置键设置唯一的值
     * @param key 键
     * @param value 要设置的值
     */
    void set(K key, V value);

    /**
     * 设置下面的值
     * @param values 值
     */
    void setAll(Map<K, V> values);

    /**
     * 返回此{@code MultiValueMap}下所有键的第一个值
     * @return 代表此映射的单一的值
     */
    Map<K, V> toSingleValueMap();
}
