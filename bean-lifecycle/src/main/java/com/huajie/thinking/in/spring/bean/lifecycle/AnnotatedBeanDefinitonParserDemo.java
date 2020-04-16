package com.huajie.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解解析 BeanDefinition 示例
 */
public class AnnotatedBeanDefinitonParserDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
        //注册当前类（非 @Component class）
        reader.register(AnnotatedBeanDefinitonParserDemo.class);
        //Bean 名称生成来自于 BeanNameGenerator,注册实现 AnnotatedBeanNameGenerator
        AnnotatedBeanDefinitonParserDemo demo = beanFactory.getBean("annotatedBeanDefinitonParserDemo",
                AnnotatedBeanDefinitonParserDemo.class);

        System.out.println(demo);
    }
}
