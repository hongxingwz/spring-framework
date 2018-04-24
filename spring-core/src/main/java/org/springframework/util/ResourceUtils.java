package org.springframework.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author jianglei
 * @since 2018/4/19
 */
public class ResourceUtils {

    public static final String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 把给定的URL替换成URI实例，
     * 首先把"%20"替换成空白。
     * @param url 要转换成URI实现的URL
     * @return URI实例
     * @throws URISyntaxException 如果位置不是有效的URI
     */
    public static URI toURI(URL url) throws URISyntaxException {
        return toURI(url.toString());
    }

    /**
     * 创建一个位置字符趾的URI实现，
     * 首先把"%20"替换成空白。
     * @param location 要转换成URI实现的资源字符串
     * @return URI实例
     * @throws URISyntaxException 如果位置不是有效的URI
     */
    public static URI toURI(String location) throws URISyntaxException {
        return new URI(StringUtils.replace(location, " ", "%20"));
    }
}
