package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * @author jianglei
 * @since 2018/4/20
 */
public class FatalBeanException extends BeansException {

    public FatalBeanException(String msg) {
        super(msg);
    }

    public FatalBeanException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
