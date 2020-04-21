package com.huajie.thinking.in.spring.dependency.lookup;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全 依赖查找示例
 */
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类{Configuration.class}
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //演示 BeanFactory#getBean 安全性
        displayBeanFactoryGetBean(applicationContext);
        //演示 ObjectFactory#getObject 安全性
        displayObjectFactoryGetObject(applicationContext);
        //演示 ObjectProvider#ifAvailable 安全性
        displayObjectProviderIfAvailable(applicationContext);
        //演示 ListableBeanFactory#getBeansOfType 安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        //演示 ObjectProvider#stream 安全性
        displayObjectProviderStreamOps(applicationContext);
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        printBeansException("displayObjectProviderStreamOps",()->{
            ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
            beanProvider.stream().forEach(System.out::println);
        });
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory applicationContext) {
        printBeansException("displayListableBeanFactoryGetBeansOfType",()->{
            applicationContext.getBeansOfType(User.class);
        });
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        printBeansException("displayObjectProviderIfAvailable", () -> {
            ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
            System.out.println(beanProvider.getIfAvailable(User::createUser));
        });
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        printBeansException("displayObjectFactoryGetObject", () -> {
            ObjectFactory<User> beanProvider = applicationContext.getBeanProvider(User.class);
            beanProvider.getObject();
        });
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("===========================" );
        System.err.println("Source from : " + source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
