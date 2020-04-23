package com.huajie.thinking.in.spring.dependency.injection;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Collection;


/**
 * 注解驱动依赖注入过程
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired
    private User user;
    // DependencyDescriptor 元信息
    // 必须 (required = true)
    // 实时注入 (eager = true)
    // 通过类型 (User.class)
    // 字段名称 (user)
    // 是否首要 (Primary = true)

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);
        applicationContext.refresh();
        AnnotationDependencyInjectionResolutionDemo beanDemo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        System.out.println(beanDemo.user);
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
