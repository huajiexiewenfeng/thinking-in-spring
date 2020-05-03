package com.huajie.thinking.in.spring.configuration.metadata;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * 基于 Java 注解装载 Spring IOC 容器配置元信息
 */
//将当前类作为 Configuration Class
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource(value = "classpath:/META-INF/user-bean-definitions.properties", encoding = "gbk")
@PropertySource(value = "classpath:/META-INF/user-bean-definitions.properties", encoding = "gbk")//可以写多个 jdk8 @Repeatable
public class AnnotatedSpringIoCContainerMetaConfigurationDemo {

    @Bean
    public User configuredUser(@Value(value = "${usr.name}") String name) {
        return User.createUser(name);
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration Class
        context.register(AnnotatedSpringIoCContainerMetaConfigurationDemo.class);
        // 启动
        context.refresh();
        Map<String, User> beansOfType = context.getBeansOfType(User.class);
        printfEach(beansOfType);
        // 关闭
        context.close();
    }

    public static void printfEach(Map<String, User> map) {
        for (Map.Entry<String, User> entry : map.entrySet()) {
            System.out.printf("User Bean name : %s , content : %s \n", entry.getKey(), entry.getValue());
        }
    }
}
