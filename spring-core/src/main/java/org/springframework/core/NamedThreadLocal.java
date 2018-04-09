package org.springframework.core;

import org.springframework.core.util.Assert;

/**
 * {@link ThreadLocal}子类，在{@link #toString()}方法
 * 暴露了指定的名字（允许内省）
 *
 * @author jianglei
 * @since 2018/4/9
 */
public class NamedThreadLocal<T> extends ThreadLocal<T> {

    private final String name;

    public NamedThreadLocal(String name) {
        Assert.hasText(name, "Name must not be empty");
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
