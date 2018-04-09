package org.springframework.core.test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author jianglei
 * @since 2018/4/8
 */
public class Test02 {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Method[] declaredMethods = Test02.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            Parameter[] parameters = declaredMethod.getParameters();
            for (Parameter parameter : parameters) {
                String name = parameter.getName();
                System.out.println(name);
            }
        }


        Class<Test02> test02Class = Test02.class;
        Test02 test02 = test02Class.newInstance();
    }
}
