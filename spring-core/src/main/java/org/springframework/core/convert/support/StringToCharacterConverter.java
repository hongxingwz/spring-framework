package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;

/**
 * 将一个字符串转换为字符
 * @author jianglei
 * @since 2018/4/9
 */
final class StringToCharacterConverter implements Converter<String, Character>{

    @Override
    public Character convert(String source) {
        if (source.isEmpty()) {
            return null;
        }

        if (source.length() > 1) {
            throw new IllegalArgumentException(
                    "Can only convert a [String] with length of 1 to a [Character]; String value '" + source + "' has length of " + source.length()
            );
        }

        return source.charAt(0);
    }
}
