package org.springframework.core.env;

import org.springframework.core.util.ObjectUtils;

/**
 * @author jianglei
 * @since 2018/4/3
 */
public abstract class EnumerablePropertySource<T> extends PropertySource<T> {

    public EnumerablePropertySource(String name, T source) {
        super(name, source);
    }

    public EnumerablePropertySource(String name) {
        super(name);
    }

    @Override
    public boolean containsProperty(String name) {
        return ObjectUtils.containsElement(getPropertyNames(), name);
    }

    public abstract String[] getPropertyNames();

}
