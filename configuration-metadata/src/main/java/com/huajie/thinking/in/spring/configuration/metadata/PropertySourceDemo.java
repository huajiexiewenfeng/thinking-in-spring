package com.huajie.thinking.in.spring.configuration.metadata;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * 外部化配置示例
 */
@PropertySource(value = "classpath:/META-INF/user-bean-definitions.properties", encoding = "gbk")
public class PropertySourceDemo {

    @Bean
    public User user(@Value(value = "${usr.name}") String name) {
        return User.createUser(name);
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 扩展 Environment 中的 propertySources
        // 这个操作必须在 context.refresh() 之前完成
        Map<String,Object> propertySource = new HashMap<>();
        propertySource.put("usr.name","new-小仙");
        MapPropertySource ms = new MapPropertySource("first-property-source",propertySource);
        context.getEnvironment().getPropertySources().addFirst(ms);
        // 注册当前类作为 Configuration Class
        context.register(PropertySourceDemo.class);
        // 启动
        context.refresh();
        Map<String, User> beansOfType = context.getBeansOfType(User.class);
        printfEach(beansOfType);
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        System.out.println(propertySources);

        // 关闭
        context.close();
    }

    public static void printfEach(Map<String, User> map) {
        for (Map.Entry<String, User> entry : map.entrySet()) {
            System.out.printf("User Bean name : %s , content : %s \n", entry.getKey(), entry.getValue());
        }
    }
}
