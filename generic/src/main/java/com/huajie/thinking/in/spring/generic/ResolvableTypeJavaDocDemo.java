package com.huajie.thinking.in.spring.generic;

import org.springframework.core.ResolvableType;

import java.util.HashMap;
import java.util.List;

/**
 * @author ：xwf
 * @date ：Created in 2020-9-27 23:45
 */
public class ResolvableTypeJavaDocDemo {

    private HashMap<Integer, List<String>> myMap;

    public void example() throws NoSuchFieldException {
        ResolvableType t = ResolvableType.forField(getClass().getDeclaredField("myMap"));
        t.getSuperType(); // AbstractMap<Integer, List<String>>
        t.asMap(); // Map<Integer, List<String>>
        t.getGeneric(0).resolve(); // Integer
        t.getGeneric(1).resolve(); // List
        t.getGeneric(1); // List<String>
        t.resolveGeneric(1, 0); // String
    }

    public static void main(String[] args) throws NoSuchFieldException {
        new ResolvableTypeJavaDocDemo().example();
    }
}
