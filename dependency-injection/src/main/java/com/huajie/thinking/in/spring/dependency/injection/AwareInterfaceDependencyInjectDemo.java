package com.huajie.thinking.in.spring.dependency.injection;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于注解实现 方法 注入示例
 */
public class AwareInterfaceDependencyInjectDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;

    private static ApplicationContext context;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AwareInterfaceDependencyInjectDemo.class);

        applicationContext.refresh();
        System.out.println(beanFactory.getBean("user"));
        System.out.println(beanFactory == applicationContext.getBeanFactory());
        System.out.println(context == applicationContext);
        applicationContext.close();
    }


    @Bean
    public User user() {
        User user = new User();
        user.setName("xwf-bean-aware");
        user.setAge(20);
        return user;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
