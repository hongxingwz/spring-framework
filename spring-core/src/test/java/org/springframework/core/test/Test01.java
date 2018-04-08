package org.springframework.core.test;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author jianglei
 * @since 2018/4/4
 */
public class Test01 {

    private Map<String, String> refMap = new HashMap<>();

    @Before
    public void init() {
        refMap.put("A", "B");
        refMap.put("B", "C");
        refMap.put("C", "D");
        refMap.put("D", "E");
        refMap.put("E", "F");
    }

    @Test
    public void test02() {
        String a = conanicalName("A");
        System.out.println(a);
    }

    private String conanicalName(String name) {
        String conanicalName = name;
        String resolvedName;
        do {
            resolvedName = refMap.get(conanicalName);
            if (resolvedName != null) {
                conanicalName = resolvedName;
            }
        } while (resolvedName != null);

        return conanicalName;
    }

    @Test
    public void test01() {
        Map<String, String> getenv = System.getenv();
        Set<String> strings = getenv.keySet();
        System.out.println(strings.size());
    }
}
