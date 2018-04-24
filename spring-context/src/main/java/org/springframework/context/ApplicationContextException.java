package org.springframework.context;

import org.springframework.beans.factory.FatalBeanException;

/**
 * @author jianglei
 * @since 2018/4/20
 */
public class ApplicationContextException extends FatalBeanException {

    public ApplicationContextException(String msg) {
        super(msg);
    }

    public ApplicationContextException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
