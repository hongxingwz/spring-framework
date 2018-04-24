package org.springframework.context;

import java.util.EventObject;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public abstract class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 61142010398780880L;

    private final long timestamp;

    public ApplicationEvent(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }
}
