package org.springframework.core;

/**
 * 管理别名的通用接口。用于服务更顶层的接口
 * {@link org.springframework.beans.factory.support.BeanDefinitionRegistry}
 * @author jianglei
 * @since 2018/4/4
 */
public interface AliasRegistry {

    /**
     * 给定一个名字，给他注册别名。
     * @param name 规范名
     * @param alias 要注册的别名
     * @throws IllegalStateException 如果别名早就被使用了
     * 并且不能被覆盖
     */
    void registerAlias(String name, String alias);

    /**
     * 把从注册表中删除指定的别名
     * @param alias 要删除的别名
     */
    void removeAlias(String alias);

    /**
     * 确定给定的名字是否是别名
     * (不是真正的注册组件的名字)
     * @param name 要检查的名字
     * @return 是否给定的名字是别名
     */
    boolean isAlias(String name);

    /**
     * 如果定义的话，返回指定名字的别名。
     * @param name 要检查的用于获取别名的名字
     * @return 别名，如果没有的话返回none
     */
    String[] getAliases(String name);
}
