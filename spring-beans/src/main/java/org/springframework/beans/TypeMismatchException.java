package org.springframework.beans;

/**
 * @author jianglei
 * @since 2018/4/20
 */
@SuppressWarnings("serial")
public class TypeMismatchException extends PropertyAccessException{

    public TypeMismatchException(String msg) {
        super(msg);
    }

    @Override
    public String getErrorCode() {
        return null;
    }
}
