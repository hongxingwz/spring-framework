package org.springframework.core.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jianglei
 * @since 2018/4/9
 */
public class Test03 {

    @Test
    @AliasFor(value = "jianglei", attribute = "jianglei")
    public void test01() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<? extends Test03> clz = this.getClass();
        Method test01 = clz.getMethod("test01");
        boolean annotationPresent = test01.isAnnotationPresent(Test.class);
        Assert.assertTrue(annotationPresent);

        AliasFor annotation = test01.getAnnotation(AliasFor.class);
        String value = annotation.value();
        String attribute = annotation.attribute();

        System.out.println(value);
        System.out.println(attribute);


        Class<? extends AliasFor> aClass = annotation.getClass();
        Method value1 = aClass.getDeclaredMethod("value");
        Object invoke = value1.invoke(annotation);
        System.out.println(invoke);


        System.out.println("--------------");
        Annotation[] annotations = test01.getAnnotations();
        for (Annotation anno : annotations) {
            System.out.println(anno);
        }


    }
}
