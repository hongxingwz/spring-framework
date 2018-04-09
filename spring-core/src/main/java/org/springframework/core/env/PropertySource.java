package org.springframework.core.env;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * @author jianglei
 * @since 2018/4/2
 */
public abstract class PropertySource<T> {

    protected final Log logger = LogFactory.getLog(getClass());

    protected final String name;

    protected final T source;


    /**
     * 用给定的名字和源对象创建一个新的{@code PropertySource}。
     */
    public PropertySource(String name, T source) {
        Assert.hasText(name, "Property source name must contain at least one character");
        Assert.notNull(source, "Property source must not be null");
        this.name = name;
        this.source = source;
    }

    /**
     * 用给定的名字和一个新对象实例作为源创建一个新的{@code PropertySource}。
     * <p>经常用于测试场景，当创建一个匿名实现从不查询真实的源，即而不用编写hard-code值
     */
    @SuppressWarnings("unchecked")
    public PropertySource(String name) {
        this(name, (T) new Object());
    }

    /**
     * 返回{@code PropertySource}的名字。
     */
    public String getName() {
        return name;
    }

    /**
     * 返回{@code PropertySource}下面包含的源对象。
     */
    public T getSource() {
        return source;
    }

    /**
     * 返回此{@code PropertySource}是否包含指定的名字。
     * <p>此实现仅检查{@link #getProperty(String)}方法返回的值是不是null，
     * 子类可以实现更有效的算法如果可以的话。
     * @param name 要查询的属性名
     */
    public boolean containsProperty(String name) {
        return (getProperty(name) != null);
    }

    /**
     * 返回与指定名字相关联的值，
     * 如果没有找到的话返回{@code null}
     * @param name 要寻找的属性
     * @return
     */
    public abstract Object getProperty(String name);

    /**
     * {@code PropertySource}对象等于下面给定的对象
     * <ul>
     *     <li>他们是相同的实例</li>
     *     <li>两个对象的{@code name}属性相等</li>
     * </ul>
     * <p>
     *     没有别的属性除了{@code name}被评估
     * </p>
     */
    @Override
    public boolean equals(Object obj) {
        return (this == obj || (obj instanceof PropertySource &&
                ObjectUtils.nullSafeEquals(this.name, ((PropertySource<?>) obj).name)));
    }

    /**
     * 返回由{@code PropertySource}对象{@code name}属性的hash值
     */
    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(this.name);
    }

    /**
     * 如果当前的日志级别不包括debug的话则产生简单的输出（类型和名字）。
     * 如果debug级别被启用，生产详细的输出包括PropertySource实例的hash
     * 值。和每个属性的键/值对。
     * <p>冗长的信息在系统属性或环境变量其可以包含好多键值对作为属性源时很
     * 有用，但是潜在的导致读取异常和日志信息时很麻烦
     * @see Log#isDebugEnabled()
     */
    @Override
    public String toString() {
        if (logger.isDebugEnabled()) {
            return getClass().getSimpleName() + "@" + System.identityHashCode(this) + " {name='" + this.name + "', properties=" + this.source + "}";
        } else {
            return getClass().getSimpleName() + " {name='" + this.name + "'}";
        }
    }

    /**
     * 返回一个{@code PropertySource}实现仅用于集合比较。
     * <p>主要用于内部使用，但是给定一个{@code PropertySource}集合对象，可以像下面这样使用：
     * <pre class="code">
     * {@code
     * List<PropertySource<?>> sources = new ArrayList<PropertySource<?>>();
     * sources.add(new MapPropertySource("sourceA", mapA);
     * sources.add(new MapPropertySource("sourceB", mapB);
     * assert sources.contains(PropertySource.named("sourceA");;
     * assert sources.contains(PropertySource.named("sourceB"));
     * assert !sources.contains(PropertySource.named("sourceC"));
     * }
     * </pre>
     * {@code PropertySource}除了{@code equals(Object)}, {@code hashCode()}, 和{@code toString()}
     * 方法被调用，其它都将会抛出{@code UnsupportedOperationException}
     * @param name 要比较的{@code PropertySource}被创建和返回的名字
     */
    public static PropertySource<?> named(String name) {
        return new ComparisonPropertySource(name);
    }

    /**
     * 在应用程序上下文创建时真正的属性源不能被提前初始化的情况下
     * {@code PropertySource} 用于作为占位符。例如，一个基于{@code ServletContext}
     * 的属性源一定要等到{@code ServletContext}对象可用时其才可用。在这种情况下，
     * 一个stub被用于保持属性源默认的位置/顺序，在context刷新时被替换。
     * TODO: 2018/4/3 注释没有写完
     */
    public static class StubPropertySource extends PropertySource<Object> {

        public StubPropertySource(String name) {
            super(name, new Object());
        }

        /**
         * Always returns {@code null}
         */
        @Override
        public Object getProperty(String name) {
            return null;
        }
    }

    /**
     * @see PropertySource#named(String)
     */
    static class ComparisonPropertySource extends StubPropertySource {

        private static final String USAGE_ERROR =
                "ComparisonPropertySource instance are for use with collection comparison only";

        public ComparisonPropertySource(String name) {
            super(name);
        }

        @Override
        public Object getSource() {
            throw new UnsupportedOperationException(USAGE_ERROR);
        }
        @Override
        public boolean containsProperty(String name) {
            throw new UnsupportedOperationException(USAGE_ERROR);
        }

        @Override
        public Object getProperty(String name) {
            throw new UnsupportedOperationException(USAGE_ERROR);
        }
    }

}
