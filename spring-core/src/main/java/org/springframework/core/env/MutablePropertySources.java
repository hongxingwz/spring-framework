package org.springframework.core.env;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jianglei
 * @since 2018/4/3
 */
public class MutablePropertySources implements PropertySources {

    private final Log logger;

    private final List<PropertySource<?>> propertySourceList = new CopyOnWriteArrayList<>();

    /**
     * 创建一个新的{@link MutablePropertySources}对象
     */
    public MutablePropertySources() {
        this.logger = LogFactory.getLog(getClass());
    }

    /**
     * 从指定的propertySource对象中创建一个新的{@code MutablePropertySources},
     * 保持包含的{@code PropertySource}的原始顺序。
     * @param propertySources
     */
    public MutablePropertySources(PropertySources propertySources) {
        this();
        for (PropertySource<?> propertySource : propertySources) {
            addLast(propertySource);
        }
    }

    /**
     * 创建一个新的{@link MutablePropertySources}对象并继承指定的logger,
     * 通常是相关的{@link Environment}
     * @param logger
     */
    MutablePropertySources(Log logger) {
        this.logger = logger;
    }

    @Override
    public boolean contains(String name) {
        return this.propertySourceList.contains(PropertySource.named(name));
    }

    @Override
    public PropertySource<?> get(String name) {
        int index = this.propertySourceList.indexOf(PropertySource.named(name));
        return (index != -1 ? this.propertySourceList.get(index) : null);
    }

    @Override
    public Iterator<PropertySource<?>> iterator() {
        return this.propertySourceList.iterator();
    }

    public void addFirst(PropertySource<?> propertySource) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Adding PropertySource '%s' with highest search precedence", propertySource.getName()));
        }
        removeIfPresent(propertySource);
        this.propertySourceList.add(0, propertySource);
    }

    public void addLast(PropertySource<?> propertySource) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Adding PropertySource '%s' with lowest search precedence", propertySource.getName()));
        }
        removeIfPresent(propertySource);
        this.propertySourceList.add(propertySource);
    }

    public void addBefore(String relativePropertySourceName, PropertySource<?> propertySource) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Adding PropertySource '%s' with search precedence immediately higher than '%s'", propertySource.getName(), relativePropertySourceName));
        }
        assertLegalRelativeAddition(relativePropertySourceName, propertySource);
        removeIfPresent(propertySource);
        int index = assertPresentAndGetIndex(relativePropertySourceName);
        addAtIndex(index, propertySource);
    }

    public void addAfter(String relativePropertySourceName, PropertySource<?> propertySource) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Adding PropertySource '%s' with search precedence immediately lower than '%s'", propertySource.getName(), relativePropertySourceName));
        }
        assertLegalRelativeAddition(relativePropertySourceName, propertySource);
        removeIfPresent(propertySource);
        int index = assertPresentAndGetIndex(relativePropertySourceName);
        addAtIndex(index + 1, propertySource);
    }

    /**
     * 返回指定属性源的顺序，如果没有找到的话，返回{@code -1}
     */
    public int precedenceOf(PropertySource<?> propertySource) {
        return this.propertySourceList.indexOf(propertySource);
    }

    /**
     * 删除并返回给定名字的属性源，如果没有找到返回{@code null}。
     * @param name 要寻找和删除属性源的名字
     */
    public PropertySource<?> remove(String name) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Removing PropertySource '%s'", name));
        }

        int index = this.propertySourceList.indexOf(PropertySource.named(name));
        return (index != -1 ? this.propertySourceList.remove(index) : null);
    }

    /**
     * 用给定的属性源对象替换给定名字的属性源
     * @param name 要寻找和替换的属性源的名字
     * @param propertySource 代替换的属性源
     * @throws IllegalArgumentException 如果指定名字的属性源没有出现的话
     * @see #contains
     */
    public void replace(String name, PropertySource<?> propertySource) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Replacing PropertySource '%s' with '%s'", name, propertySource.getName()));
        }

        int index = assertPresentAndGetIndex(name);
        this.propertySourceList.set(index, propertySource);
    }

    /**
     * 返回包含的{@link PropertySource}对象的数量
     */
    public int size() {
        return this.propertySourceList.size();
    }

    @Override
    public String toString() {
        return this.propertySourceList.toString();
    }

    /**
     * 确保给定的属性源不被添加到自己
     */
    protected void assertLegalRelativeAddition(String relativePropertySourceName, PropertySource<?> propertySource) {
        String newPropertySourceName = propertySource.getName();
        if (relativePropertySourceName.equals(newPropertySourceName)) {
            throw new IllegalArgumentException(String.format("PropertySource named '%s' cannot be added relative to itself", relativePropertySourceName));
        }
    }

    /**
     * 删除指定的属性源
     * @param propertySource
     */
    protected void removeIfPresent(PropertySource<?> propertySource) {
        this.propertySourceList.remove(propertySource);
    }

    /**
     * 在列中指定的索引外添加指定的属性源
     */
    private void addAtIndex(int index, PropertySource<?> propertySource) {
        removeIfPresent(propertySource);
        this.propertySourceList.add(index, propertySource);
    }

    /**
     * 断言命名的属性源存在并返回其索引
     * @param name 用于查找的{@linkplain PropertySource#getName() 属性源的名字}
     * @return IllegalArgumentException 如果命名的属性源没有出现
     */
    private int assertPresentAndGetIndex(String name) {
        int index = this.propertySourceList.indexOf(PropertySource.named(name));
        if (index == -1) {
            throw new IllegalArgumentException(String.format("PropertySource named '%s' does not exist", name));
        }
        return index;
    }
}
