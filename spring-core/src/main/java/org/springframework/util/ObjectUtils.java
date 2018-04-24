package org.springframework.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * @author jianglei
 * @since 2018/4/3
 */
public abstract class ObjectUtils {

    private static final int INITIAL_HASH = 7;
    private static final int MULTIPLIER = 31;

    private static final String EMPTY_STRING = "";
    private static final String NULL_STRING = "null";
    private static final String ARRAY_START = "{";
    private static final String ARRAY_END = "}";
    private static final String EMPTY_ARRAY = ARRAY_START + ARRAY_END;
    private static final String ARRAY_ELEMENT_SEPARATOR = ", ";


    /**
     * 返回给定的{@code throwable}是否是已检查异常：
     * 也就是既不是{@code RuntimeException}也不是{@code Error}。
     * @param ex 要被检测的{@code throwable}
     * @return {@code throwable}是否是已检查异常
     */
    public static boolean isCheckedException(Throwable ex) {
        return !(ex instanceof RuntimeException || ex instanceof Error);
    }

    /**
     * 确定给定的对象是否是数组：
     * 对象数组或基本类型数组
     */
    public static boolean isArray(Object obj) {
        return (obj != null && obj.getClass().isArray());
    }

    /**
     * 确定指定的数组是否是空的
     * i.e. {@code null}或长度为0
     * @param array 要检测的数组
     * @see #isEmpty(Object)
     */
    public static boolean isEmpty(Object[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 确定给定的对象是否是空
     * 此方法支持下面的对象类型：
     * <ul>
     *  <li>{@code Array}: 如果其长度为0，则被认为是空</li>
     *  <li>{@code CharSequence}: 如果其长度为0，则被认为是空</li>
     *  <li>{@code Collection}: 委托{@link Collection#isEmpty()}</li>
     *  <li>{@code Map}: 委托{@link Map#isEmpty()}</li>
     * </ul>
     * <p>如果指定的对象不为null且其不是上述所支持的类型，此方法返回{@code false}。
     * @param obj 要检测的对象
     * @return {@code true} 如果对象是{@code null}或<em>空</em>
     * @since 4.2
     * @see ObjectUtils#isEmpty(Object[])
     * @see StringUtils#hasLength(CharSequence)
     * @see StringUtils#isEmpty(Object)
     * @see CollectionUtils#isEmpty(Collection)
     * @see CollectionUtils#isEmpty(Map)
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }

        // else
        return false;
    }

    /**
     * 检测给定的数组包含给定的元素
     * @param array 要检测的数组(可以为{@code null})，
     * 如果为{@code null}则永远返回{@code false}
     * @param element 用于检测的元素
     * @return 在给定的数组里是否找到了给定的元素
     */
    public static boolean containsElement(Object[] array, Object element) {
        if (array == null) {
            return false;
        }

        for (Object arrayEle : array) {
            if (nullSafeEquals(arrayEle, element)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 检测给定的枚举常量数组是否具有指定名字的常数
     * @param enumValues 要检测的枚举值，通常是调用MyEnum.values()的返回值
     * @param constant 要查找的查量值(一定不能为null或空字符串)
     * @return 在给定的数组里是否找到常数
     */
    public static boolean containsConstant(Enum<?>[] enumValues, String constant) {
        return containsConstant(enumValues, constant, false);
    }

    /**
     * 检测给定的枚举常量数组是否具有指定名字的常数
     * @param enumValues 要检测的枚举值，通常是调用MyEnum.values()的返回值
     * @param constant 要查找的查量值(一定不能为null或空字符串)
     * @param caseSensitive 是否大小写决定匹配
     * @return 在给定的数组里是否找到常数
     */
    public static boolean containsConstant(Enum<?>[] enumValues, String constant, boolean caseSensitive) {
        for (Enum<?> candidate : enumValues) {
            if (caseSensitive ?
                    candidate.toString().equals(constant) :
                    candidate.toString().equalsIgnoreCase(constant)) {
                return true;
            }
        }
        return false;
    }

    //---------------------------------------------------------------------------------
    // Convenience methods for content-based equality/hash-code handling
    //---------------------------------------------------------------------------------

    /**
     * 确定给定对象的相等性，返回{@true}如果都为{@code null}或者{@code false}如果仅有一个为{@code null}
     * <p>用{@code Arrays.equals}比较数组，执行的是数组元素相等性的检测而不是数组引用
     * @param o1 用于比较的第一个对象
     * @param o2 用于比较的第二个数组
     * @return 给定的对象是否相等
     * @see Object#equals(Object)
     * @see Arrays#equals
     */
    public static boolean nullSafeEquals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (o1.equals(o2)) {
            return true;
        }
        if (o1.getClass().isArray() && o2.getClass().isArray()) {
            return arrayEquals(o1, o2);
        }
        return false;
    }

    /**
     * 使用{@code Arrays.equals}比较给定的数组，执行基于数组元素相等性
     * 的检测而不是数组引用
     * @param o1 比较的第一个数组
     * @param o2 比较的第二个数组
     * @return 给定的对象是否相等
     * @see #nullSafeEquals(Object, Object)
     * @see Arrays#equals
     */
    private static boolean arrayEquals(Object o1, Object o2) {
        if (o1 instanceof Object[] && o2 instanceof Object[]) {
            return Arrays.equals((Object[]) o1, (Object[]) o2);
        }
        if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
            return Arrays.equals((boolean[]) o1, (boolean[]) o2);
        }
        if (o1 instanceof byte[] && o2 instanceof byte[]) {
            return Arrays.equals((byte[]) o1, (byte[]) o2);
        }
        if (o1 instanceof char[] && o2 instanceof char[]) {
            return Arrays.equals((char[]) o1, (char[]) o2);
        }
        if (o1 instanceof double[] && o2 instanceof double[]) {
            return Arrays.equals((double[]) o1, (double[]) o2);
        }
        if (o1 instanceof float[] && o2 instanceof float[]) {
            return Arrays.equals((float[]) o1, (float[]) o2);
        }
        if (o1 instanceof int[] && o2 instanceof int[]) {
            return Arrays.equals((int[]) o1, (int[]) o2);
        }
        if (o1 instanceof long[] && o2 instanceof long[]) {
            return Arrays.equals((long[]) o1, (long[]) o2);
        }
        if (o1 instanceof short[] && o2 instanceof short[]) {
            return Arrays.equals((short[]) o1, (short[]) o2);
        }
        return false;
    }

    /**
     * 返回指定对象的hash值；通常是{@code Object#hashCode()}。
     * 如果对象是个数组的话，此方法将会委托此类中的任何{@code nullSafeHashCode}
     * 方法。如果object为{@code null}的话，此方法返回0。
     * @see Object#hashCode()
     * @see #nullSafeHashCode(Object[])
     * @see #nullSafeHashCode(boolean[])
     * @see #nullSafeHashCode(byte[])
     * @see #nullSafeHashCode(char[])
     * @see #nullSafeHashCode(double[])
     * @see #nullSafeHashCode(float[])
     * @see #nullSafeHashCode(int[])
     * @see #nullSafeHashCode(long[])
     * @see #nullSafeHashCode(short[])
     */
    public static int nullSafeHashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj.getClass().isArray()) {
            if (obj instanceof Object[]) {
                return nullSafeHashCode((Object[]) obj);
            }
            if (obj instanceof boolean[]) {
                return nullSafeHashCode((boolean[]) obj);
            }
            if (obj instanceof byte[]) {
                return nullSafeHashCode((byte[]) obj);
            }
            if (obj instanceof char[]) {
                return nullSafeHashCode((char[]) obj);
            }
            if (obj instanceof double[]) {
                return nullSafeHashCode((double[]) obj);
            }
            if (obj instanceof float[]) {
                return nullSafeHashCode((float[]) obj);
            }
            if (obj instanceof int[]) {
                return nullSafeHashCode((int[]) obj);
            }
            if (obj instanceof long[]) {
                return nullSafeHashCode((long[]) obj);
            }
            if (obj instanceof short[]) {
                return nullSafeHashCode((short[]) obj);
            }
        }
        return obj.hashCode();
    }

    /**
     * 返回基于指定数组的内容所计算的hash值。
     * 如果{@code array}为{@code null}，此方法返回0。
     */
    public static int nullSafeHashCode(Object[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (Object element : array) {
            hash = MULTIPLIER * hash + nullSafeHashCode(element);
        }
        return hash;
    }

    /**
     * 返回基于指定数组的内容所计算的hash值。
     * 如果{@code array}为{@code null}，此方法返回0。
     */
    public static int nullSafeHashCode(boolean[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (boolean element : array) {
            hash = MULTIPLIER * hash + hashCode(element);
        }
        return hash;
    }

    /**
     * 返回基于指定数组的内容所计算的hash值。
     * 如果{@code array}为{@code null}，此方法返回0。
     */
    public static int nullSafeHashCode(byte[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (byte element : array) {
            hash = MULTIPLIER * hash + element;
        }
        return hash;
    }

    /**
     * 返回基于指定数组的内容所计算的hash值。
     * 如果{@code array}为{@code null}，此方法返回0。
     */
    public static int nullSafeHashCode(char[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (char element : array) {
            hash = MULTIPLIER * hash + element;
        }
        return hash;
    }

    /**
     * 返回基于指定数组的内容所计算的hash值。
     * 如果{@code array}为{@code null}，此方法返回0。
     */
    public static int nullSafeHashCode(double[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (double element : array) {
            hash = MULTIPLIER * hash + hashCode(element);
        }
        return hash;
    }

    /**
     * 返回基于指定数组的内容所计算的hash值。
     * 如果{@code array}为{@code null}，此方法返回0。
     */
    public static int nullSafeHashCode(float[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (float element : array) {
            hash = MULTIPLIER * hash + hashCode(element);
        }

        return hash;
    }

    /**
     * 返回基于指定数组的内容所计算的hash值。
     * 如果{@code array}为{@code null}，此方法返回0。
     */
    public static int nullSafeHashCode(int[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (int element : array) {
            hash = MULTIPLIER * hash + element;
        }
        return hash;
    }

    /**
     * 返回基于指定数组的内容所计算的hash值。
     * 如果{@code array}为{@code null}，此方法返回0。
     */
    public static int nullSafeHashCode(long[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (long element : array) {
            hash = MULTIPLIER * hash + hashCode(element);
        }
        return hash;
    }

    /**
     * 返回基于指定数组的内容所计算的hash值。
     * 如果{@code array}为{@code null}，此方法返回0。
     */
    public static int nullSafeHashCode(short[] array) {
        if (array == null) {
            return 0;
        }
        int hash = INITIAL_HASH;
        for (short element : array) {
            hash = MULTIPLIER * hash + element;
        }
        return hash;
    }

    /**
     * 返回与{@link Boolean#hashCode()}相同的值。
     */
    public static int hashCode(boolean bool) {
        return (bool ? 1231 : 1237);
    }

    /**
     * 返回与{@link Double#hashCode()}相同的值。
     */
    public static int hashCode(double dbl) {
        return hashCode(Double.doubleToLongBits(dbl));
    }

    /**
     * 返回与{@link Float#hashCode()}相同的值。
     */
    public static int hashCode(float flt) {
        return Float.floatToIntBits(flt);
    }


    /**
     * 返回与{@link Long#hashCode()}相同的值。
     * @see Long#hashCode()
     */
    public static int hashCode(long lng) {
        return (int) (lng ^ (lng >>> 32));
    }


    /**
     * 妆给定的数组(可以是基本类型数据)转换成为一个对象数组(是基本类型的包装类型)。
     * <p>source如果为{@code null}将转换成一个空的对象数组。
     *
     * @param source 数组(可能是基本数组类型数组)
     * @return 相关的对象数组(从不为null)
     * @throws IllegalArgumentException 如果参数不是一个数组的话
     */
    public static Object[] toObjectArray(Object source) {
        if (source instanceof Object[]) {
            return (Object[]) source;
        }
        if (source == null) {
            return new Object[0];
        }
        if (!source.getClass().isArray()) {
            throw new IllegalArgumentException("Source is not an array: " + source);
        }

        int length = Array.getLength(source);
        if (length == 0) {
            return new Object[0];
        }
        Class<?> wrapperType = Array.get(source, 0).getClass();
        Object[] newArray = (Object[]) Array.newInstance(wrapperType, length);
        for(int i = 0; i < length; i++) {
            newArray[i] = Array.get(source, i);
        }

        return newArray;
    }

    public static String identityToString(Object obj) {
        if (obj == null) {
            return EMPTY_STRING;
        }
        return obj.getClass().getName() + "@" + getIdentityHexString(obj);
    }

    public static String getIdentityHexString(Object ob) {
        return Integer.toHexString(System.identityHashCode(ob));
    }

    public static String getDisplayString(Object obj) {
        if (obj == null) {
            return EMPTY_STRING;
        }
        return nullSafeToString(obj);
    }

    private static String nullSafeToString(Object obj) {
        return null;
    }

}
