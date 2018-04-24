package org.springframework.util;

import java.util.*;

/**
 * @author jianglei
 * @since 2018/4/2
 */
public abstract class StringUtils {

    private static final String FOLDER_SEPARATOR = "/";

    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";

    private static final String TOP_PATH = "..";

    private static final String CURRENT_PATH = ".";

    private static final char EXTENSION_SEPARATOR = '.';

    /**
     * 检测给定的{@code String}是否是空的。
     * <p>此方法接受任何对象作为参数，把它和{@code null}和空字符串作比较。
     * 作为结果，此方法对于非null非字符串对象从不返回{@code true}。
     * <p>Object作为方法签名对于通常的属性处理代码--处理字符串但是类型是
     * 对象
     * @param str 候选的字符串
     */
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }


    /**
     * 检测指定的{@code CharSequence}既不为{@code null}长度也不为0。
     * <p>注意：此方法返回{@code true}对于一个{@code CharSequence}仅由空白字符组成。
     * </p>
     * <pre class="code">
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength("") = false
     * StringUtils.hasLength(" ") = true
     * StringUtils.hasLength("Hello") = true
     * </pre>
     * @param str {@code CharSequence} 要被检测的字符串（可以为{@code null}）
     * @return {@code true}如果{@code CharSequence}既不是{@code null}且其有长度
     * @see #hasText(String)
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * 检测给定的{@code String}既不是{@code null}，其长度也不是0。
     * <p>注意：此方法返回{@code true}对于一个{@code String}仅由空白字符组成。
     * @param str {@code String} 要被检测的字符串（可以为{@code null}）
     * @return {@code true}如果{@code String}既不是{@code null}且其有长度
     * @see #hasLength(CharSequence)
     * @see #hasText(String)
     */
    public static boolean hasLength(String str) {
        return (str != null && !str.isEmpty());
    }

    /**
     * 检测给定的{@code CharSequence}是否包含真正的<em>text</em>。
     * <p>更具体的来讲，此方法返回{@code true}如果{@code CharSequence}
     * 不为{@code null}，且其长度大于0，且其包含至少一个非空白字符。
     * </p>
     * <pre class="code">
     * StringUtils.hasText(null) = false
     * StringUtils.hasText("") = false
     * StringUtils.hasText(" ") = false
     * StringUtils.hasText("12345") = true
     * StringUtils.hasText(" 12345 ") = true
     * </pre>
     * @param str {@code CharSequence} 要被检测的字符序列（可以为{@code null}）
     * @return {@code true}如果{@code CharSequence}不为{@code null}，其长度大于0，
     * 且其不仅仅只包含空白字符
     * @see Character#isWhitespace
     */
    public static boolean hasText(CharSequence str) {
        return (hasLength(str) && containsText(str));
    }

    /**
     * 检测给定的{@code CharSequence}是否包含真正的<em>text</em>。
     * <p>更具体的来讲，此方法返回{@code true}如果{@code CharSequence}
     * 不为{@code null}，且其长度大于0，且其包含至少一个非空白字符。
     * @param str {@code String} 要被检测的字符串（可以为{@code null}）
     * @return {@code true}如果{@code String}不为{@code null}，其长度大于0，
     * 且其不仅仅只包含空白字符
     */
    public static boolean hasText(String str) {
        return (hasLength(str) && containsText(str));
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for(int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 除去给定{@code String}的所有空白：
     * 首部，尾部，中间
     * @param str 要检查的字符串
     * @return 去除后的字符串
     * @see Character#isWhitespace
     */
    public static String trimAllWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }

        int len = str.length();
        StringBuilder sb = new StringBuilder(str.length());
        for(int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (!Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 把一个被分割的列表字符串转换成一个{@code String}数组
     * <p>一个单一的{@code delimiter}可以由超过一个的字符组成，
     * 但其会仍被认为是一个单一的分割字符串，而不是一组潜在的分割
     * 字符，这与{@link #tokenizeToStringArray}。
     * </p>
     * @param str 输入的字符串
     * @param delimiter 元素简的分割符（这就是一个单一的分割符，而不是一组潜在的分割字符）
     * e.g. "\r\n\f"将会删除在字符串中所有的新行和换行符
     * @return 列表的字符串数组形式
     */
    public static String[] delimitedListToStringArray(String str, String delimiter) {
        return delimitedListToStringArray(str, delimiter, null);
    }

    /**
     * 把一个被分割的列表字符串转换成一个{@code String}数组
     * <p>一个单一的{@code delimiter}可以由超过一个的字符组成，
     * 但其会仍被认为是一个单一的分割字符串，而不是一组潜在的分割
     * 字符，这与{@link #tokenizeToStringArray}。
     * </p>
     * @param str 输入的字符串
     * @param delimiter 元素简的分割符（这就是一个单一的分割符，而不是一组潜在的分割字符）
     * @param charsToDelete 一系列要删除的字符；对于删除不想要的挑选符来说很有用：
     * e.g. "\r\n\f"将会删除在字符串中所有的新行和换行符
     * @return 列表的字符串数组形式
     */
    public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
        if (str == null) {
            return new String[0];
        }

        if (delimiter == null) {
            return new String[]{str};
        }

        List<String> result = new ArrayList<>();
        if ("".equals(delimiter)) {
            for (int i = 0; i < str.length(); i++) {
                result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
            }
        } else {
            int pos = 0;
            int delPos;
            while ((delPos = str.indexOf(delimiter, pos)) != -1) {
                result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
            }

            if (str.length() > 0 && pos <= str.length()) {
                result.add(deleteAny(str.substring(pos), charsToDelete));
            }
        }
        return toStringArray(result);
    }

    /**
     * 把一个逗号分割的列表转换成字符串数组
     * @param str 输入的字符串
     * @return 一个字符数组，如果输入为空则为空数组
     */
    public static String[] commaDelimitedListToStringArray(String str) {
        return delimitedListToStringArray(str, ",");
    }

    /**
     * 把指定的{@code Collection}转换成一个{@code String}数组
     * <p>{@code Collection}一定要仅包含{@code String}
     * @param collection 要复制的{@code Collection}
     * @return {@code String} 数组
     */
    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null) {
            return null;
        }

        return collection.toArray(new String[collection.size()]);
    }


    /**
     * 把给定的{@code Enumeration}复制成{@code String}数组。
     * {@code Enumeration}仅包含{@code String}元素。
     * @param enumeration 要复制的{@code Enumeration}
     * @return {@code String} 数组
     */
    public static String[] toStringArray(Enumeration<String> enumeration) {
        if (enumeration == null) {
            return null;
        }

        List<String> list = Collections.list(enumeration);
        return list.toArray(new String[list.size()]);
    }

    /**
     * 把一个{@link Collection}转换成一个分割的{@code String}（e.g. CSV）。
     * <p>对于实现{@code toString()}比较有用</p>
     * @param coll 要转换的{@code Collection}
     * @param delim 使用的分割符（通常是一个","）
     * @param prefix 每个元素开始的字符串
     * @param suffix 每个元素结束的字符串
     * @return 分割后的字符串
     */
    public static String collectionToDelimitedString(Collection<?> coll, String delim, String prefix, String suffix) {
        if (CollectionUtils.isEmpty(coll)) {
            return "";
        }
        Iterator<?> it = coll.iterator();
        StringBuilder sb = new StringBuilder();
        while (true) {
            sb.append(prefix).append(it.next()).append(suffix);
            if (!it.hasNext()) {
                return sb.toString();
            }
            sb.append(delim);
        }
    }

    /**
     * 把一个{@link Collection}转换成一个分割的{@code String}（e.g. CSV）。
     * <p>对于实现{@code toString()}比较有用</p>
     * @param coll 要转换的{@code Collection}
     * @param delim 使用的分割符（通常是一个","）
     * @return 分割后的字符串
     */
    public static String collectionToDelimitedString(Collection<?> coll, String delim) {
        return collectionToDelimitedString(coll, delim, "", "");
    }

    /**
     * 把一个{@link Collection}转换成一个分割的{@code String}（e.g. CSV）。
     * <p>对于实现{@code toString()}比较有用</p>
     * @param coll 要转换的{@code Collection}
     * @return 分割后的字符串
     */
    public static String collectionToDelimitedString(Collection<?> coll) {
        return collectionToDelimitedString(coll, ",");
    }

    /**
     * 删除任何在给定字符串中的字符串
     * @param inString 原始的{@code String}
     * @param charsToDelete 要删除的一系列字符
     * E.g. 'az\n"将会删除 'a', 'z', 和新行
     * @return 处理后的字符串
     */
    public static String deleteAny(String inString, String charsToDelete) {
        if (!hasLength(inString) || !hasLength(charsToDelete)) {
            return inString;
        }

        StringBuilder sb = new StringBuilder(inString.length());
        for(int i = 0; i < inString.length(); i++) {
            char c = inString.charAt(i);
            if (charsToDelete.indexOf(c) == -1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 将一个字符串内的子串替换成其他的字符串
     * @param inString 要检测的字符串
     * @param oldPattern 要替换的字符串
     * @param newPattern 要插入的字符串
     * @return 替换后的字符串
     */
    public static String replace(String inString, String oldPattern, String newPattern) {
        if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
            return inString;
        }

        int index = inString.indexOf(oldPattern);
        if (index == -1) {
            return inString;
        }

        int capacity = inString.length();
        if (newPattern.length() > oldPattern.length()) {
            capacity += 16;
        }

        StringBuilder sb = new StringBuilder(capacity);
        int pos = 0;
        int patLen = oldPattern.length();

        while (index >= 0) {
            sb.append(inString.substring(pos, index));
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }

        sb.append(inString.substring(pos));
        return sb.toString();

    }



}
