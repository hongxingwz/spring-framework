package org.springframework.core.convert.converter;

/**
 * @author jianglei
 * @since 2018/4/3
 */
public interface Converter<S, T> {

    T convert(S source);
}
