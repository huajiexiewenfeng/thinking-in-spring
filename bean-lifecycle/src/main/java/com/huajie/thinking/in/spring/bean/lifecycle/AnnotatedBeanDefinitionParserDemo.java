package com.huajie.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解解析 BeanDefinition 示例
 */
public class AnnotatedBeanDefinitionParserDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCountBefore = beanFactory.getBeanDefinitionCount();
        //注册当前类（非 @Component class）
        reader.register(AnnotatedBeanDefinitionParserDemo.class);
        reader.register(Test.class);
        int beanDefinitionCountAfter = beanFactory.getBeanDefinitionCount();
        int beanDefinitionCount = beanDefinitionCountAfter - beanDefinitionCountBefore;
        System.out.println("Bean 定义注册的数量："+beanDefinitionCount);
        //Bean 名称生成来自于 BeanNameGenerator,注册实现 AnnotatedBeanNameGenerator
        AnnotatedBeanDefinitionParserDemo demo = beanFactory.getBean("annotatedBeanDefinitionParserDemo",
                AnnotatedBeanDefinitionParserDemo.class);

        System.out.println(demo);
    }

    public class Test{

    }
}
