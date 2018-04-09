package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;

import java.nio.charset.Charset;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public class StringToCharsetConverter implements Converter<String, Charset> {

    @Override
    public Charset convert(String source) {
        return Charset.forName(source);
    }
}
