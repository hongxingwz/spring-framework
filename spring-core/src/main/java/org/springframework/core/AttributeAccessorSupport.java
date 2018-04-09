package org.springframework.core;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * {@link AttributeAccessor AttributeAccessors}的支持类，提供了
 * 对所有方法的实现。由子类继承。
 *
 * <p>如果子类和所有的属性值是{@link Serializable}则{@link Serializable}
 * @author jianglei
 * @since 2018/4/8
 */
public abstract class AttributeAccessorSupport implements AttributeAccessor, Serializable{

    /** 具有字符串键和对像值的映射 */
    private final Map<String, Object> attributes = new LinkedHashMap<>(0);

    @Override
    public void setAttribute(String name, Object value) {
        Assert.notNull(name, "Name must not be null");
        if (value != null) {
            this.attributes.put(name, value);
        } else {
            removeAttribute(name);
        }
    }

    @Override
    public Object getAttribute(String name) {
        Assert.notNull(name, "Name must not be null");
        return this.attributes.get(name);
    }

    @Override
    public Object removeAttribute(String name) {
        Assert.notNull(name, "Name must not be null");
        return this.attributes.remove(name);
    }

    @Override
    public boolean hasAttribute(String name) {
        Assert.notNull(name, "Name must not be null");
        return this.attributes.containsKey(name);
    }

    @Override
    public String[] attributeNames() {
        return this.attributes.keySet().toArray(new String[this.attributes.size()]);
    }

    /**
     * 把提供的{@code AttributeAccessor}的属性复制到此 {@code AttributeAccessor}
     * @param source 要复制的{@code AttributeAccessor}
     */
    protected void copyAttributesFrom(AttributeAccessor source) {
        Assert.notNull(source, "Source must not be null");
        String[] attributeNames = source.attributeNames();
        for (String attributeName : attributeNames) {
            setAttribute(attributeName, source.getAttribute(attributeName));
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof AttributeAccessorSupport)){ return false;}

        AttributeAccessorSupport that = (AttributeAccessorSupport) o;

        return  attributes.equals(that.attributes);
    }

    @Override
    public int hashCode() {
        return attributes.hashCode();
    }
}
