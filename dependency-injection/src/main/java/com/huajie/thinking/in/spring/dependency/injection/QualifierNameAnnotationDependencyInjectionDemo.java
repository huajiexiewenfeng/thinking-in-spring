package com.huajie.thinking.in.spring.dependency.injection;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;


/**
 * {@link Qualifier} 注解依赖注入
 */
public class QualifierNameAnnotationDependencyInjectionDemo {

    @Autowired
    @Qualifier("user")
    private User user;

    @Autowired
    @Qualifier
    private User user_group;

    @Autowired
    private User user1;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierNameAnnotationDependencyInjectionDemo.class);
        applicationContext.refresh();
        QualifierNameAnnotationDependencyInjectionDemo beanDemo = applicationContext.getBean(QualifierNameAnnotationDependencyInjectionDemo.class);
        System.out.println("Qualifier名称：" + beanDemo.user);
        System.out.println("普通：" + beanDemo.user1);
        System.out.println("分组：" + beanDemo.user_group);
        applicationContext.close();
    }


    @Bean
    public User user() {
        return createUser("xwf-bean");
    }

    @Bean
    @Qualifier
    public User userGroup() {
        return createUser("xwf-bean-group");
    }

    @Bean
    @Primary
    public User superUser() {
        return createUser("super-xwf-bean");
    }

    private static User createUser(String name) {
        User user = new User();
        user.setName(name);
        return user;
    }

}
