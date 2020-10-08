package com.huajie.thinking.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link PropertySource} 示例
 *
 * @author ：xwf
 * @date ：Created in 2020-10-8 23:35
 * @see PropertySource
 */
public class EnvironmentPropertySourceChangeDemo {

    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnvironmentPropertySourceChangeDemo.class);
        ConfigurableEnvironment environment = context.getEnvironment();

        MutablePropertySources propertySources = environment.getPropertySources();

        Map<String, Object> sources = new HashMap<>();
        sources.put("user.name", "小仙");

        MapPropertySource propertySource = new MapPropertySource("first-property-source", sources);

        propertySources.addFirst(propertySource);

        for (PropertySource ps : environment.getPropertySources()) {
            System.out.println(ps.toString());
        }

        // 启动 spring 应用上下文
        context.refresh();

        EnvironmentPropertySourceChangeDemo demo = context.getBean(EnvironmentPropertySourceChangeDemo.class);
        System.out.println(demo.userName);

        // 关闭 spring 应用上下文
        context.close();
    }

}
