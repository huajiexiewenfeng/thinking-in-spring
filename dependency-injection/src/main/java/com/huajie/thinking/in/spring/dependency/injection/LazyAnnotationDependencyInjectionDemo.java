package com.huajie.thinking.in.spring.dependency.injection;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Collection;


/**
 * 延迟注解依赖注入
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> providerUser;

    @Autowired
    private ObjectProvider<Collection<User>> providerUsers;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);
        applicationContext.refresh();
        LazyAnnotationDependencyInjectionDemo beanDemo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println(beanDemo.user);
        System.out.println(beanDemo.providerUser.getIfAvailable());
        System.out.println(beanDemo.user == beanDemo.providerUser.getIfAvailable());
        beanDemo.providerUser.forEach(System.out::println);
        System.out.println("=======================");
        System.out.println(beanDemo.providerUsers.getObject());
        applicationContext.close();
    }


    @Bean
    @Primary
    public User user() {
        return createUser("xwf-bean");
    }

    @Bean
    public User user2() {
        return createUser("xwf-bean2");
    }


    private static User createUser(String name) {
        User user = new User();
        user.setName(name);
        return user;
    }

}
