package org.springframework.core.env;

import org.junit.Test;
import org.springframework.core.env.PropertySource;

/**
 * @author jianglei
 * @since 2018/4/3
 */
public class PropertySourceTest {

    @Test
    public void test01() {
        PropertySource<?> named = PropertySource.named("aaa");
        System.out.println(named);
    }
}
