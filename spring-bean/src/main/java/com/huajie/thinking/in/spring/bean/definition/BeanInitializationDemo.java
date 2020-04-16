package com.huajie.thinking.in.spring.bean.definition;

import com.huajie.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.huajie.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 初始化示例
 */
@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //依赖查找
        UserFactory bean = applicationContext.getBean(UserFactory.class);
        System.out.println(bean.createUser());
        //关闭
        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory",destroyMethod = "destroyUserFactory")
    @Lazy
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }
}
