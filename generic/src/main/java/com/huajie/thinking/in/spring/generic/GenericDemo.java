package com.huajie.thinking.in.spring.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Java 泛型示例
 *
 * @author ：xwf
 * @date ：Created in 2020-9-26 22:59
 */
public class GenericDemo {
    public static void main(String[] args) {
        // Java 7 Diamond 语法
        Collection<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        // 编译时错误
//        list.add(1);

        // 泛型擦写
        Collection temp = list;
        // 编译通过
        temp.add(1);

        System.out.println(list);
    }
}
