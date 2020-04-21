package com.huajie.thinking.in.spring.dependency.lookup;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过{@link org.springframework.beans.factory.ObjectProvider} 进行依赖查找
 */
public class ObjectProviderDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类{Configuration.class}
        applicationContext.register(ObjectProviderDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        lookupByObjectProvider(applicationContext);
        lookupGetIfAvailable(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
//        Iterable<String> stringIterable = beanProvider;
//        for (String str : stringIterable) {
//            System.out.println(str);
//        }
        beanProvider.stream().forEach(System.out::println);
    }

    private static void lookupGetIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
//        User ifAvailable = beanProvider.getIfAvailable(()->User.createUser());
        User ifAvailable = beanProvider.getIfAvailable(User::createUser);
        System.out.println(ifAvailable);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
//        User ifAvailable = beanProvider.getIfAvailable(()->User.createUser());
        beanProvider.ifAvailable(System.out::println);
    }


    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }

    @Bean
    @Primary
    //如果没有定义name 那么方法名就是 Bean 的名称
    public String helloWorld() {
        return "Hello,World";
    }

    @Bean
    //如果没有定义name 那么方法名就是 Bean 的名称
    public String message() {
        return "Message";
    }

    @Bean
    public User user() {
        return User.createUser("ifAvailable-user");
    }

}
