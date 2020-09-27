package com.huajie.thinking.in.spring.generic;

import org.springframework.core.ResolvableType;

/**
 * {@link ResolvableType} 示例
 *
 * @author ：xwf
 * @date ：Created in 2020-9-27 23:38
 * @see ResolvableType
 */
public class ResolvableTypeDemo {
    public static void main(String[] args) {
        // 工厂创建
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

        resolvableType.getSuperType();// ArrayList
        resolvableType.getSuperType().getSuperType();// AbstractList

        System.out.println(resolvableType.asCollection().resolve()); // 获取 Raw 类型
        System.out.println(resolvableType.asCollection().resolveGeneric(0));// 获取泛型参数类型

    }
}
