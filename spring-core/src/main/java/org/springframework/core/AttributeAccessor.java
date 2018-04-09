package org.springframework.core;

/**
 * 此接口定义了一个普通的合同用于给一个武断的对象赋值和访问元信息。
 * @author jianglei
 * @since 2018/4/8
 */
public interface AttributeAccessor {

    /**
     * 设置由{@code name}定义的属性由{@code value}提供的值。
     * 如果{@code value}的值为{@code null}，属性会被{@link #removeAttribute 移除}。
     * <p>通常情况下，用户应该小心阻止其它属性的覆盖通过使用全限定类，可以使用类或包名作为
     * 前缀。
     * @param name 唯一的属性键
     * @param value 要设置的值
     */
    void setAttribute(String name, Object value);

    /**
     * 返回由{@code name}标识的属性值.
     * 如果属性不存在的话则返回{@code null}。
     * @param name 唯一的属性键
     * @return 如果存在，返回属性当前值
     */
    Object getAttribute(String name);

    /**
     * 删除由{@code name}标识的属性并返回其值。
     * 如果{@code name}下面没有找到属性值则返回{@code null}。
     * @param name 唯一的属性键
     * @return 如果存在的话，返回属性值
     */
    Object removeAttribute(String name);

    /**
     * 返回{@code true}如果由{@code name}标识的属性存在。
     * 否则返回{@code false}。
     * @param name 唯一的属性键
     */
    boolean hasAttribute(String name);

    /**
     * 返回所有属性的名字。
     */
    String[] attributeNames();
}
