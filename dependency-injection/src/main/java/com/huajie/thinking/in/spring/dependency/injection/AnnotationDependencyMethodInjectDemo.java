package com.huajie.thinking.in.spring.dependency.injection;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于注解实现 方法 注入示例
 */
public class AnnotationDependencyMethodInjectDemo {

    private UserHolder userHolder;

    private UserHolder userHolder_resource;

    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Resource
    public void initUserHolder2(UserHolder userHolder_resource) {
        this.userHolder_resource = userHolder_resource;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyMethodInjectDemo.class);

        applicationContext.refresh();

        AnnotationDependencyMethodInjectDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectDemo.class);
        System.out.println("userHolder:" + demo.userHolder);
        System.out.println("userHolder_resource:" + demo.userHolder_resource);
        System.out.println("userHolder 和 userHolder_resource 比较:" +( demo.userHolder == demo.userHolder_resource));
        applicationContext.close();
    }

    @Bean
    public User user() {
        User user = new User();
        user.setName("xwf-bean-field");
        user.setAge(20);
        return user;
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
