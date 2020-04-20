package com.huajie.thinking.in.spring.bean.definition;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        //通过 BeanDefinition 注册 API 实现
        // 1.通过命名方式注册
        registerUserBeanDefinition(applicationContext,"definition-user");
        // 2.非命名方式注册
        registerUserBeanDefinition(applicationContext);
        //启动 Spring 应用上下文
        applicationContext.refresh();
        //1.通过 @Bean 方式定义

        //2.通过 @Component 方式定义

        //3.通过 @Import 来进行导入

        Map<String, Config> beansOfType = applicationContext.getBeansOfType(Config.class);
        printEach(beansOfType);

        Map<String, User> users = applicationContext.getBeansOfType(User.class);
        printEach(users);

        applicationContext.close();
    }

    private static void printEach(Map<String, ?> beansOfType) {
        System.out.println("=====");
        beansOfType.entrySet().stream().forEach(System.out::println);
        System.out.println("=====");
    }

    /**
     * 命名 Bean 的注册方式
     *
     * @param registry
     * @param beanName
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("age", 18)
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "xwf");

        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            //非命名方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }


    @Component //定义当前类最为 Spring Bean 组件
    public static class Config {

        @Bean(name = {"user", "xwf-user"})
        public User user() {
            User user = new User();
            user.setAge(18);
            user.setName("xwf");
            user.setId(123L);
            return user;
        }

    }

}
