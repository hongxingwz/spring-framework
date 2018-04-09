package org.springframework.core;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public class Constants {

    /**
     * 内省类的名字
     */
    private final String className;

    /**
     * 从字符串字段名映射到对象值的映射
     */
    private final Map<String, Object> fieldCache = new HashMap<>();

    /**
     * 创建一个新的常量转换类来包装给定的类。
     * <p>所有的<b>public</b> static final变量都将会暴露，不管他们是
     * 什么类型。
     * @param clazz 要解析的类
     * @throws IllegalArgumentException 如果提供的{@code clazz}为{@code null}的话
     */
    public Constants(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        this.className = clazz.getName();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            if (ReflectionUtils.isPublicStaticFinal(field)) {
                String name = field.getName();
                try {
                    Object value = field.get(null);
                    this.fieldCache.put(name, value);
                } catch (IllegalAccessException ex) {
                    // just leave this field and continue
                }
            }
        }
    }

    /**
     * 返回解析类的名字
     */
    public final String getClassName() {
        return this.className;
    }


    /**
     * 返回暴露的常量的数量
     */
    public final int getSize() {
        return this.fieldCache.size();
    }

    /**
     * 暴露给子类字段的缓存：
     * 一个字符串映射对象值的映射。
     */
    protected final Map<String, Object> getFieldCache() {
        return this.fieldCache;
    }

    /**
     * 返回常量值并转型为Number。
     * @param code 字段的名字(永不为{@code null})
     * @return 数值
     * @throws ConstantException 如果字段名字找不到，
     * 或其值的类型与Number不兼容
     */
    public Number asNumber(String code) throws ConstantException {
        Object obj = asObject(code);
        if (!(obj instanceof Number)) {
            throw new ConstantException(this.className, code, "not a Number");
        }
        return (Number) obj;
    }

    /**
     * 以字符串形式返回常量值。
     * @param code 字段的名字(永不为{@code null})
     * @return 字符串值
     * 即使不是字符串也会工作(调用{@code toString()})。
     * @see #asObject
     * @throws ConstantException 如果字段名字找不到
     */
    public String asString(String code) throws ConstantException{
        return asObject(code).toString();
    }

    /**
     * 解析给定字符串(大小写都可以)，返回相关的常量名的值如果
     * 可以被解析的话
     * @param code 字段的名字(永不为{@code null})
     * @return 对象值
     * @throws ConstantException 如果没有这样的字段
     */
    public Object asObject(String code) throws ConstantException{
        Assert.notNull(code, "Code must not be null");
        String codeToUse = code.toUpperCase(Locale.ENGLISH);
        Object val = this.fieldCache.get(codeToUse);
        if (val == null) {
            throw new ConstantException(this.className, codeToUse, "not found");
        }
        return val;
    }

    /**
     * 返回指定组的所有常量的名字。
     * <p>注意此方法假设所有的常量命名
     * 都是依据Java标准的常量命名规则(i.e. 所有的都大写)。
     * @param namePrefix 要查找的常量名的前缀(可以为{@code null})
     * @return 常量名集合
     */
    public Set<String> getNames(String namePrefix) {
        String prefixToUse = (namePrefix != null ? namePrefix.trim().toUpperCase(Locale.ENGLISH) : "");
        Set<String> names = new HashSet<>();
        for (String code : fieldCache.keySet()) {
            if (code.startsWith(prefixToUse)) {
                names.add(code);
            }
        }
        return names;
    }

    public Set<String> getNamesForProperty(String propertyName) {
        return getNames(propertyToConstantNamePrefix(propertyName));
    }

    public Set<String> getNamesForSuffix(String nameSuffix) {
        String suffixToUse = (nameSuffix != null ? nameSuffix.trim().toUpperCase(Locale.ENGLISH) : "");
        Set<String> names = new HashSet<>();
        for (String code : fieldCache.keySet()) {
            if (code.endsWith(suffixToUse)) {
                names.add(code);
            }
        }

        return names;
    }


    public Set<Object> getValues(String namePrefix) {
        String prefixToUse = (namePrefix != null ? namePrefix.toUpperCase(Locale.ENGLISH) : "");
        Set<Object> values = new HashSet<>();
        for (String code : fieldCache.keySet()) {
            if (code.startsWith(prefixToUse)) {
                values.add(this.fieldCache.get(code));
            }
        }
        return values;
    }

    public Set<Object> getValuesForProperty(String propertyName) {
        return getValues(propertyToConstantNamePrefix(propertyName));
    }

    public Set<Object> getValueForSuffix(String nameSuffix) {
        String suffixToUse = (nameSuffix != null ? nameSuffix.trim().toUpperCase(Locale.ENGLISH) : "");
        Set<Object> values = new HashSet<>();
        for (String code : fieldCache.keySet()) {
            if (code.endsWith(suffixToUse)) {
                values.add(this.fieldCache.get(code));
            }
        }
        return values;
    }

    public String toCode(Object value, String namePrefix) throws ConstantException {
        String prefixToUse = (namePrefix != null ? namePrefix.trim().toUpperCase(Locale.ENGLISH) : "");
        for (Map.Entry<String, Object> entry : fieldCache.entrySet()) {
            if (entry.getKey().startsWith(prefixToUse) && entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        throw new ConstantException(this.className, prefixToUse, value);
    }


    public String toCodeForProperty(Object value, String propertyName) throws ConstantException {
        return toCode(value, propertyToConstantNamePrefix(propertyName));
    }

    public String toCodeForSuffix(Object value, String nameSuffix) throws ConstantException {
        String suffixToUse = (nameSuffix != null ? nameSuffix.trim().toLowerCase(Locale.ENGLISH) : "");
        for (Map.Entry<String, Object> entry : fieldCache.entrySet()) {
            if (entry.getKey().endsWith(suffixToUse) && entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        throw new ConstantException(this.className, suffixToUse, value);
    }

    /**
     * 把给定的bean属性名转换成常量前缀。
     * 使用一个能用的命名风格：把所有的小写字符转换成大写，
     * 在大写字符前添加下划线。
     * <p>例如："imageSize" -> "IMAGE_SIZE"<br>
     *     例如："imagesize" -> "IMAGESIZE"。<br>
     *     例如："ImageSize" -> "_IMAGE_SIZE"。<br>
     *     例如："IMAGESIZE" -> "_I_M_A_G_E_S_I_Z_E"。
     * @param propertyName bean属性名
     * @return 相关的常量名前缀
     */
    public String propertyToConstantNamePrefix(String propertyName) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0, len = propertyName.length(); i < len; i++ ) {
            char c = propertyName.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append('_').append(c);
            } else {
                sb.append(Character.toUpperCase(c));
            }
        }
        return sb.toString();
    }
}
