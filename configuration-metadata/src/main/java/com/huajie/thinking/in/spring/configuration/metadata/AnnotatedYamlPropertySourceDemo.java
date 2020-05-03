package com.huajie.thinking.in.spring.configuration.metadata;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * 基于 Java 注解的 yaml 外部化配置示例
 */
@PropertySource(name="yamlPropertySource",value = "classpath:/META-INF/user.yaml",factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {

    @Bean
    public User user(@Value(value = "${usr.name}") String name) {
        return User.createUser(name);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration Class
        context.register(AnnotatedYamlPropertySourceDemo.class);
        // 启动
        context.refresh();

        User user = context.getBean("user", User.class);

        System.out.println(user);
        // 关闭
        context.close();
    }
}
