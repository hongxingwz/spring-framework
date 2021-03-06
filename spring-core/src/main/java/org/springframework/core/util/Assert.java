package org.springframework.core.util;

/**
 * @author jianglei
 * @since 2018/4/2
 */
public abstract class Assert {

    /**
     * 断言给定的字符串包含有效的文本内容；也就是，其一定不为能{@code null}
     * 一定要包含至少一个非空字符。
     * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
     * @param text 要检测的字符串
     * @param message 如果断言失败使用的异常信息
     * @see StringUtils#hasText
     * @throws IllegalArgumentException 如果给定的字符串不包含有效的文本内容
     */
    public static void hasText(String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个对象为{@code null}。
     * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
     * @param object 要检测的对象
     * @param message 如果断言失败使用的异常信息
     * @throws IllegalArgumentException 如果对象不为{@code null}的话
     */
    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个对象不为{@code null}。
     * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
     * @param object 要检测的对象
     * @param message 如果断言失败使用的异常信息
     * @throws IllegalArgumentException 如果对象为{@code null}
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言一个数组要包含元素；也就是，其一定不能为{@code null}且
     * 必须包含至少一个元素。
     * <pre class="code">Assert.notEmpty(array, "The array must contain elements");</pre>
     * @param array 要检测的数组
     * @param message 如果断言失败使用的异常信息
     * @throws IllegalArgumentException 如果数组对象为{@code null}或不包含任何元素
     */
    public static void notEmpty(Object[] array, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

}
