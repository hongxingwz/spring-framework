package org.springframework.util;

/**
 * 用于解析字符串的策略接口。
 * 由{@link org.springframework.beans.factory.config.ConfigurableBeanFactory}使用。
 * @author jianglei
 * @since 2018/4/4
 */
public interface StringValueResolver {

    /**
     * 解析给定的字符串值，例如解析占位符。
     * @param strVal 原始的字符串值(永远不为{@code null}).
     * @return 解析后的字符串(可以为null当解析一个null值的话)，
     * 也可以是原始的字符串（当没有站位符或忽略不可解析的站位符）
     * @throws IllegalArgumentException 在字符串值不可解析的情况下。
     */
    String resolveStringValue(String strVal);
}
