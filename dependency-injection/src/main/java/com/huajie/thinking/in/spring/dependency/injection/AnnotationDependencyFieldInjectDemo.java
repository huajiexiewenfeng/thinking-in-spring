package com.huajie.thinking.in.spring.dependency.injection;

import com.huajie.thinking.in.spring.dependency.injection.annotation.InjectUser;
import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * 基于注解实现字段注入示例
 */
public class AnnotationDependencyFieldInjectDemo {

    @Autowired
    private UserHolder userHolder;

    @Resource
    private UserHolder userHolder_resource;

    @InjectUser
    private User inject_user;

    @Bean(name = "injectUserAnnotationBeanPostProcessor")
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyFieldInjectDemo.class);

        applicationContext.refresh();

        // @Autowired 不支持 static 修饰的属性，所以还是采用依赖查找的方式
        AnnotationDependencyFieldInjectDemo demo = applicationContext.getBean(AnnotationDependencyFieldInjectDemo.class);
        System.out.println("userHolder:" + demo.userHolder);
        System.out.println("userHolder_resource:" + demo.userHolder_resource);
        System.out.println("userHolder 和 userHolder_resource 比较:" + (demo.userHolder == demo.userHolder_resource));
        System.out.println("inject_user:" + demo.inject_user);
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
