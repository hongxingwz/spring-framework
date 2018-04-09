package org.springframework.core.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.SpringVersion;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
        String getName;
        do {
            getName = refMap.get(conanicalName);
            if (getName != null) {
                conanicalName = getName;
            }
        } while (getName != null);

        return conanicalName;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method conanicalName = Test01.class.getDeclaredMethod("conanicalName", String.class);
        Parameter[] parameters = conanicalName.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println(parameter.isNamePresent());
            System.out.println(parameter.getName());
        }
    }
    @Test
    public void test04() throws NoSuchMethodException {
        Method conanicalName = this.getClass().getDeclaredMethod("conanicalName", String.class);
        Parameter[] parameters = conanicalName.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println(parameter.isNamePresent());
            System.out.println(parameter.getName());
        }
    }

    @Test
    public void test01() {
        Map<String, String> getenv = System.getenv();
        Set<String> strings = getenv.keySet();
        System.out.println(strings.size());
    }

    @Test
    public void test03() {
        String version = SpringVersion.getVersion();
        System.out.println(version);
    }
}
