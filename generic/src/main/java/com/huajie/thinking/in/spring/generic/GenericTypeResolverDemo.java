package com.huajie.thinking.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;

/**
 * {@link GenericTypeResolver}
 *
 * @author ：xwf
 * @date ：Created in 2020-9-27 22:12
 * @see GenericTypeResolver
 */
public class GenericTypeResolverDemo {
    public static void main(String[] args) throws NoSuchMethodException {
        Class clazz = GenericTypeResolverDemo.class;

        displayReturnTypeGenericInfo(clazz, List.class, "getString", null);
        // String 是 Comparable 接口的具体化
        // public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
        displayReturnTypeGenericInfo(clazz, Comparable.class, "getString", null);

        displayReturnTypeGenericInfo(clazz, List.class, "getUsers", null);

        displayReturnTypeGenericInfo(clazz, List.class, "getStringList", null);

        displayReturnTypeGenericInfo(clazz, List.class, "getStringList2", null);

        // TypeVariable
        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }

    public String getString() {
        return "Hello,World";
    }

    public <E> List<E> getUsers() {
        return Collections.emptyList();
    }

    /**
     * 泛型参数的具体化
     *
     * @return
     */
    public List<String> getStringList() {
        return Collections.emptyList();
    }

    /**
     * 泛型参数的具体化
     *
     * @return
     */
    public StringList getStringList2() {
        return null;
    }



    private static void displayReturnTypeGenericInfo(Class<?> containingClass, Class<?> genericIfc, String methodName, Class... arguments) throws NoSuchMethodException {
        Method method = containingClass.getMethod(methodName, arguments);
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, containingClass);
        // 常规类作为方法返回值类型
        System.out.println(returnType);
        // 常规类型不具备泛型参数类型
        Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc);
        System.out.println(returnTypeArgument);
    }

}
