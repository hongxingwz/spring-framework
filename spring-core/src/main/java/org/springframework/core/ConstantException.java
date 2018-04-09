package org.springframework.core;

/**
 * 当{@link Constants}被寻问一个无效的常量名时抛出此异常。
 *
 * @author jianglei
 * @since 2018/4/9
 */
@SuppressWarnings("serial")
public class ConstantException extends IllegalArgumentException {


    public ConstantException(String className, String field, String message) {
        super("Field '" + field + "' " + message + " in class [" + className + "]");
    }

    public ConstantException(String className, String namePrefix, Object value) {
        super("No '" + namePrefix + "' field with value '" + value + "' found in class [" + className + "]");
    }
}
