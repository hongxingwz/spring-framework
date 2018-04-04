package org.springframework.core.env;

/**
 * 持有一个或多个{@link PropertySource}对象。
 *
 * @author jianglei
 * @since 2018/4/3
 */
public interface PropertySources extends Iterable<PropertySource<?>>{

    /**
     * 返回是否包含给定名字的属性源
     * @param name 用于查找的{@linkplain PropertySource#getName() 属性源的名字}
     */
    boolean contains(String name);

    /**
     * 返回具有指定名字的属性源，如果没有找到返回{@code null}。
     * @param name 用于查找的{@linkplain PropertySource#getName() 属性源的名字}
     */
    PropertySource<?> get(String name);
}
