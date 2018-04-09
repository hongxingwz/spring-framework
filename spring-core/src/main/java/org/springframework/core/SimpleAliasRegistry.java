package org.springframework.core;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link AliasRegistry}接口的简单实现。
 * 服务于基类
 * {@link org.springframework.beans.factory.support.BeanDefinitionRegistry}
 * @author jianglei
 * @since 2018/4/4
 */
public class SimpleAliasRegistry implements AliasRegistry {

    /** 从别名到规范名的映射 */
    private final Map<String, String> aliasMap = new ConcurrentHashMap<>(16);

    @Override
    public void registerAlias(String name, String alias) {
        Assert.hasText(name, "'name' must not be empty");
        Assert.hasText(alias, "'alias' must not be empty");
        if (alias.equals(name)) {
            this.aliasMap.remove(alias);
        } else {
            String registeredName = this.aliasMap.get(alias);
            if (registeredName != null) {
                if (registeredName.equals(name)) {
                    return;
                }

                if (!allowAliasOverriding()) {
                    throw new IllegalStateException("Cannot register alias '" + alias + "' for name '" +
                            name + "': It is already registered for name '" + registeredName + "'");
                }
            }
            checkForAliasCircle(name, alias);
            this.aliasMap.put(alias, name);
        }
    }

    /**
     * 返回别名覆盖是否允许。
     * 默认是{@code true}
     */
    protected boolean allowAliasOverriding() {
        return true;
    }


    /**
     * 决定是否指定的名字已经被注册了别名
     * @param name 要检查的名字
     * @param alias 询查的别名
     * @return
     */
    public boolean hasAlias(String name, String alias) {
        for (Map.Entry<String, String> entry : aliasMap.entrySet()) {
            String registeredName = entry.getValue();
            if (registeredName.equals(name)) {
                String registeredAlias = entry.getKey();
                return (registeredAlias.equals(alias) || hasAlias(registeredAlias, alias));

            }
        }

        return false;
    }

    @Override
    public void removeAlias(String alias) {
        String name = this.aliasMap.remove(alias);
        if (name == null) {
            throw new IllegalArgumentException("No alias '" + alias + "' registered");
        }
    }

    @Override
    public boolean isAlias(String name) {
        return this.aliasMap.containsKey(name);
    }


    @Override
    public String[] getAliases(String name) {
        List<String> result = new ArrayList<>();
        synchronized (this.aliasMap) {
            retrieveAliases(name, result);
        }
        return StringUtils.toStringArray(result);
    }

    /**
     * 解析指定名字的所有别名
     * @param name 寻找别名的目标名
     * @param result 解析的别名列表
     */
    private void retrieveAliases(String name, List<String> result) {
        for (Map.Entry<String, String> entry : aliasMap.entrySet()) {
            String registeredName = entry.getValue();
            if (registeredName.equals(name)) {
                String alias = entry.getKey();
                result.add(alias);
                retrieveAliases(alias, result);
            }
        }
    }

    /**
     *
     * @param name
     * @param alias
     */
    protected void checkForAliasCircle(String name, String alias) {
        if (hasAlias(alias, name)) {
            throw new IllegalStateException("Cannot register alias '" + alias +
                    "' for name '" + name + "': Circular reference - '" +
                    name + "' is a direct or indirect alias for '" + alias + "' already");
        }
    }

    /**
     * 获取原始名，把别名解析为规名
     * @param name 用户指定的名字
     * @return 返回解析后的名字
     */
    public String canonicalName(String name) {
        String canonicalName = name;
        String resolvedName;
        do {
            resolvedName = this.aliasMap.get(canonicalName);
            if (resolvedName != null) {
                canonicalName = resolvedName;
            }
        } while (resolvedName != null);

        return canonicalName;
    }
}
