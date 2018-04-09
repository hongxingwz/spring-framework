package org.springframework.core.env;

import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author jianglei
 * @since 2018/4/3
 *
 * <p>例如，调用{@code getProperty("foo.bar")}将会尝试寻找原始或任何'相等'属性的值，返回第一
 * 找到的：
 * <ul>
 *     <li>{@code foo.bar} - 原始的名</li>
 *     <li>{@code foo_bar} - 用下划线代替句号</li>
 *     <li>{@code FOO.BAR} - 原始的名，转换成大写</li>
 *     <li>{@code FOO_BAR} - 下划线和大写</li>
 * </ul>
 */
public class SystemEnvironmentPropertySource extends MapPropertySource {

    public SystemEnvironmentPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }

    @Override
    public Object getProperty(String name) {
        String actualName = resolvePropertyName(name);
        if (logger.isDebugEnabled() && !name.equals(actualName)) {
            logger.debug("PropertySource '" + getName() + "' does not contain property '" + name +
                            "', but found equivalent '" + actualName + "'");
        }
        return super.getProperty(actualName);
    }

    private String resolvePropertyName(String name) {
        Assert.notNull(name, "Property name must not be null");
        String resolvedName = checkPropertyName(name);
        if (resolvedName != null) {
            return resolvedName;
        }

        String upperCasedName = name.toUpperCase();
        if (!name.equals(upperCasedName)) {
            resolvedName = checkPropertyName(upperCasedName);
            if (resolvedName != null) {
                return resolvedName;
            }
        }

        return name;
    }

    private String checkPropertyName(String name) {
        if (containsKey(name)) {
            return name;
        }

        String noDotName = name.replace('.', '_');
        if (!name.equals(noDotName) && containsKey(noDotName)) {
            return noDotName;
        }

        String noHyphenName = name.replace('-', '_');
        if (!name.equals(noHyphenName) && containsKey(noHyphenName)) {
            return noHyphenName;
        }

        String noDotNoHyphenName = noDotName.replace('-', '_');
        if (!noDotName.equals(noDotNoHyphenName) && containsKey(noDotNoHyphenName)) {
            return noDotNoHyphenName;
        }

        // Give up
        return null;
    }

    private boolean containsKey(String name) {
        return (isSecurityManagerPresent() ? this.source.keySet().contains(name) : this.source.containsKey(name));
    }

    protected boolean isSecurityManagerPresent() {
        return (System.getSecurityManager() != null);
    }
}
