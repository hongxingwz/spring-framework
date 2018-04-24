package org.springframework.core;

import java.io.IOException;

/**
 * @author jianglei
 * @since 2018/4/20
 */
@SuppressWarnings("serial")
public class NestedIOException extends IOException {

    static {
        NestedExceptionUtils.class.getName();
    }

    public NestedIOException(String message) {
        super(message);
    }

    public NestedIOException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return NestedExceptionUtils.buildMessage(super.getMessage(), getCause());
    }
}
