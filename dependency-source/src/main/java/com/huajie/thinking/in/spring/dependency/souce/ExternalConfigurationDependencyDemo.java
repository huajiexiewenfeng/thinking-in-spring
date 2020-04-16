package com.huajie.thinking.in.spring.dependency.souce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;

/**
 * 外部化配置依赖来源
 */
@Configuration
@PropertySource(value = "META-INF/default.properties", encoding = "gbk")
public class ExternalConfigurationDependencyDemo {

    @Value("${user.id:-1}")
    private Long id;

    @Value("${usr.name}")
    private String name;

    @Value("${user.resource:classpath://default.properties}")
    private Resource resource;

    @PostConstruct
    public void init() {
//        System.out.println(id);
//        System.out.println(resource);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExternalConfigurationDependencyDemo.class);

        applicationContext.refresh();
        ExternalConfigurationDependencyDemo demo = applicationContext.getBean(ExternalConfigurationDependencyDemo.class);

        System.out.println(demo.id);
        System.out.println(demo.name);
        System.out.println(demo.resource);

        applicationContext.close();
    }
}
