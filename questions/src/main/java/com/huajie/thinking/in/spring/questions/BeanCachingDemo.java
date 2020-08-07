package com.huajie.thinking.in.spring.questions;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link ObjectFactory} 延迟依赖查找示例
 *
 * @author ：xwf
 * @date ：Created in 2020-8-7 11:08
 * @see ObjectFactory
 * @see ObjectProvider
 */
public class BeanCachingDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册
        applicationContext.register(BeanCachingDemo.class);
        // 开启
        applicationContext.refresh();
        BeanCachingDemo demo = applicationContext.getBean(BeanCachingDemo.class);
        // 关闭
        applicationContext.close();
    }

    @Bean
    public User user() {
        return User.createUser("小仙");
    }
}
