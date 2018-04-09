package org.springframework.core.convert;

import org.springframework.core.NestedRuntimeException;

/**
 * 类型转换系统抛出的基类
 *
 * @author jianglei
 * @since 2018/4/9
 */
@SuppressWarnings("serial")
public abstract class ConversionException extends NestedRuntimeException {

    /**
     * 构造一个新的转换异常
     * @param message 异常信息
     */
    public ConversionException(String message) {
        super(message);
    }

    /**
     * 构造一个新的转换异常
     * @param message 异常信息
     * @param cause 原因
     */
    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
