package com.huajie.thinking.in.spring.data.java.beans;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * JavaBeans 示例
 */
public class JavaBeansDemo {
    public static void main(String[] args) throws IntrospectionException {
        // stopClass 排查类
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class,Object.class);

        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            System.out.println(propertyDescriptor);
        });

        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);
    }
}
