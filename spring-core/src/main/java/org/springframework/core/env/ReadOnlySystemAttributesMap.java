package org.springframework.core.env;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * 基于系统属性或环境变量实现的{@code Map<String, String>}。
 *
 * <p>当{@link SecurityManager}禁止访问{@link System#getProperties()}或{@link System#getenv()}
 * 时被{@link AbstractApplicationContext}使用。出于这样的原因{@link #keySet()}, {@link #entrySet()},
 * 和{@link #values()}总是返回空即使{@link #get(Object)}在实际上返回非{@code null}值如果当前的权限管理允许
 * 单独的访问键。
 *
 * @author jianglei
 * @since 2018/4/4
 */
 abstract class ReadOnlySystemAttributesMap implements Map<String, String> {

    @Override
    public boolean containsKey(Object key) {
        return (get(key) != null);
    }

    /**
     * @param key 要提取的系统属性名
     * @throws IllegalArgumentException 如果给定的键是非字符串的话
     */
    @Override
    public String get(Object key) {
        if (!(key instanceof String)) {
            throw new IllegalArgumentException(
                    "Type of key [" + key.getClass().getName() + "] must be java.lang.String");
        }

        return getSystemAttribute((String) key);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * 模板方法返回下面的系统属性
     * <p>实现通常调用{@link System#getProperty(String)}或{@link System#getenv(String)}
     * @param attributeName
     * @return
     */
    protected abstract String getSystemAttribute(String attributeName);

    // Unsupported

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String put(String key, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> values() {
        return Collections.emptySet();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return Collections.emptySet();
    }
}
