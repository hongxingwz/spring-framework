package org.springframework.core.env;

import org.springframework.core.util.StringUtils;

import java.util.*;

/**
 * 复合{@link PropertySource}实现可以迭代一系列的{@link PropertySource}实例。
 * 这在一多个属性源共享同一个名字时是非常必要的。
 *
 * <p>在Spring 4.1.2之后，此类继承{@link EnumerablePropertySource}而不是原始的
 * {@link PropertySource}，暴露了{@link #getPropertyNames()} 基于所包含源的属性
 * 名。
 *
 * @author jianglei
 * @since 2018/4/4
 */
public class CompositePropertySource extends EnumerablePropertySource<Object> {

    private final Set<PropertySource<?>> propertySources = new LinkedHashSet<>();

    /**
     * 创建一个新的{@code CompositePropertySource}
     * @param name 属性源的名字
     */
    public CompositePropertySource(String name) {
        super(name);
    }

    @Override
    public Object getProperty(String name) {
        for (PropertySource<?> propertySource : propertySources) {
            Object candidate = propertySource.getProperty(name);
            if (candidate != null) {
                return candidate;
            }
        }
        return null;
    }

    @Override
    public boolean containsProperty(String name) {
        for (PropertySource<?> propertySource : propertySources) {
            if (propertySource.containsProperty(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] getPropertyNames() {
        Set<String> names = new LinkedHashSet<>();
        for (PropertySource<?> propertySource : propertySources) {
            if (!(propertySource instanceof EnumerablePropertySource)) {
                throw new IllegalStateException(
                        "Failed to enumerate property names due to non-enumerable property source: " + propertySource);
            }
            names.addAll(Arrays.asList(((EnumerablePropertySource<?>) propertySource).getPropertyNames()));
        }
        return StringUtils.toStringArray(names);
    }

    /**
     * 把给定的{@link PropertySource}添加到链的末尾。
     * @param propertySource
     */
    public void addPropertySource(PropertySource<?> propertySource) {
        this.propertySources.add(propertySource);
    }

    /**
     * 把{@link PropertySource}添加到链的开始。
     * @param propertySource 要添加的PropertySource
     * @since 4.1
     */
    public void addFirstPropertySource(PropertySource<?> propertySource) {
        ArrayList<PropertySource<?>> existing = new ArrayList<>(this.propertySources);
        this.propertySources.clear();
        this.propertySources.add(propertySource);
        this.propertySources.addAll(existing);
    }

    /**
     * 返回此复合源持有的所有的属性源。
     * @since 4.1.1
     */
    public Collection<PropertySource<?>> getPropertySources() {
        return this.propertySources;
    }

    @Override
    public String toString() {
        return String.format("%s [name='%s', propertySources=%s",
                getClass().getSimpleName(), this.name, this.propertySources);
    }
}
