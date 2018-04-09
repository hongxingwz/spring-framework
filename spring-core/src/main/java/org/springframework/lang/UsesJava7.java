package org.springframework.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表明被注解的元素使用了Java7特定的API构造，
 * 也就是说严格要求使用Java7。
 *
 * @author jianglei
 * @since 2018/4/8
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
public @interface UsesJava7 {
}
