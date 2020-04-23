package com.huajie.thinking.in.spring.dependency.injection;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于注解实现 Constructor 注入示例
 */
public class AnnotationDependencyConstructorInjectDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyConstructorInjectDemo.class);
        applicationContext.refresh();

        UserHolder bean = applicationContext.getBean(UserHolder.class);
        System.out.println(bean);
        applicationContext.close();
    }

    @Bean
    public User user(){
        User user = new User();
        user.setName("xwf-annotation-constructor-bean");
        user.setAge(20);
        return user;
    }

    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }

}
