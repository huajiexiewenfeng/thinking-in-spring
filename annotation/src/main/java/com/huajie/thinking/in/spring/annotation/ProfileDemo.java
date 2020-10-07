package com.huajie.thinking.in.spring.annotation;

import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author ：xwf
 * @date ：Created in 2020-9-24 22:45
 */
@Configuration
public class ProfileDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();
        // 设置 Profile
        environment.setDefaultProfiles("even");
        // 注册 Configuration Class
        context.register(ProfileDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();

        System.out.println(context.getBean(Integer.class));

        // 关闭 Spring 应用上下文
        context.close();
    }


    @Bean
    @Profile("odd")
    public Integer odd() {// 奇数
        return 1;
    }

    @Bean
    @Profile("even")
//    @Conditional(EvenProfileCondition.class)
    public Integer even() {// 偶数
        return 2;
    }
}
