package org.springframework.core;

/**
 * 暴露了Spring版本的类。
 * 从jar文件获取"Implementation-Version"的manifest属性。
 *
 * <p>注解一些类加载器不会报露包的元信息，因此此类可能在所有环境下会发现不
 * 了Spring的版本。考虑使用基于返射的检测来代替：....
 * @author jianglei
 * @since 2018/4/8
 */
public class SpringVersion {

    /**
     * 返回Spring代码的全版本，如果确定不也返回{@code null}
     * @return
     */
    public static String getVersion() {
        Package pkg = SpringVersion.class.getPackage();
        return (pkg != null ? pkg.getImplementationVersion() : null);
    }

}
