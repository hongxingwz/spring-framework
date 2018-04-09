package org.springframework.core;

/**
 * 帮助类用于有能力持有嵌套异常的异常类。
 * 这是必须的，因为我们不能在不同异常类型之间共享
 * 一个基础类。
 *
 * @author jianglei
 * @since 2018/4/9
 */
public abstract class NestedExceptionUtils {

    /**
     * 把给定的基本信息和根本原因构造一个信息。
     * @param message 基本信息
     * @param cause 根本原因
     * @return 全的异常信息
     */
    public static String buildMessage(String message, Throwable cause) {
        if (cause == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(64);
        if (message != null) {
            sb.append(message).append("; ");
        }
        sb.append("nested exception is ").append(cause);
        return sb.toString();
    }

    /**
     * 从给定异常信息中提取最最里层的原因，如果存在的话。
     * @param original 原始的异常用于内省
     * @return 最里层的异常，如果没有的话返回{@code null}
     */
    public static Throwable getRootCause(Throwable original) {
        if (original == null) {
            return null;
        }
        Throwable rootCause = null;
        Throwable cause = original.getCause();
        while (cause != null && cause != rootCause) {
            rootCause = cause;
            cause = cause.getCause();
        }
        return rootCause;
    }

    /**
     * 提取给定异常最明确的异常，也就是，
     * 最里面的原因(root cause)或者异常自身。
     * <p>与{@link #getRootCause}不同的是
     * 如果没有根本原因则返回原始的异常。
     * @param original 用于内省的原始异常
     * @return 最明确的异常永不为{@code null}
     */
    public static Throwable getMostSpecificCause(Throwable original) {
        Throwable rootCause = getRootCause(original);
        return (rootCause != null ? rootCause : original);
    }
}
