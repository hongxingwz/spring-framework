package org.springframework.core;

/**
 * {@code Ordered}是一个接口由应该可以被排序的对象实现，例如
 * 在一个集合里。
 *
 * <p>真正的{@link #getOrder() 顺序}可以被解释为优先级，第一个对象
 * (顺序最小的值)具有最高的优先级。
 *
 * <p>注意这里有一个<em>priority</em>的标识接口：{@link PriorityOrdered}。
 * 由{@code PriorityOrdered}产生的顺序值永远在<em>简单</em>的{@link Ordered}对象产
 * 生的一样的顺序值之前。
 *
 * <p>查看{@link OrderComparator}的文档来获取对于{@code non-ordered}对象的排序语义。
 *
 * @author jianglei
 * @since 2018/4/8
 */
public interface Ordered {

    /**
     * 有用的常量对于最高优先级值。
     * @see java.lang.Integer#MIN_VALUE
     */
    int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    /**
     * 有用的常量对于最低优先级值。
     * @see java.lang.Integer#MAX_VALUE
     */
    int LOWEST_PRECEDENCE = Integer.MAX_VALUE;

    /**
     * 获取此对象的顺序值。
     * <p>更大的值被解释为低优先级。因而，
     * 最有最小值的对象拥有最高优先级
     * (有一点像Servlet的{@code load-on-startup}值。
     * <p>具有相同值的对象会被武断的判断排序的位置。
     * @return
     */
    int getOrder();
}
