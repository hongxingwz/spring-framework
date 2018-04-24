package org.springframework.beans;

import org.springframework.core.MethodParameter;

import java.lang.reflect.Field;

/**
 * @author jianglei
 * @since 2018/4/20
 */
public interface TypeConverter {

    <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;

    <T> T convertIfNecessary(Object value, Class<T> requiredType, MethodParameter methodParam) throws TypeMismatchException;

    <T> T convertIfNecessary(Object value, Class<T> requiredType, Field field) throws TypeMismatchException;

}
