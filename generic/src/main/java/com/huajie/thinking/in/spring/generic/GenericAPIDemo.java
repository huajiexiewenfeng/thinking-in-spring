package com.huajie.thinking.in.spring.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Java 泛型 API 示例
 *
 * @author ：xwf
 * @date ：Created in 2020\6\14 0014 17:28
 */
public class GenericAPIDemo {
    public static void main(String[] args) {
        // 原生类型 primitive types ：int long float ...
        Class intClass = int.class;

        // 数组类型 array types：int[],Object[]
        Class objectArrayClass = Object[].class;

        // 原始类型 raw types：java.lang.String
        Class rawClass = String.class;

        System.out.printf("原生类型：[%s]\n数组类型：[%s]\n原始类型：[%s]\n", intClass, objectArrayClass, rawClass);

        // 泛型参数类型 parameterized type
        ParameterizedType parameterizedType = (ParameterizedType)ArrayList.class.getGenericSuperclass();

        System.out.println(parameterizedType.toString());
        Type[] typeVariables = parameterizedType.getActualTypeArguments();
        Stream.of(typeVariables).map(TypeVariable.class::cast).forEach(System.out::println);

    }
}
