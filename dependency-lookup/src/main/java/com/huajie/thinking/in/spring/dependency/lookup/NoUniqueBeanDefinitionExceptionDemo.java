package com.huajie.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoUniqueBeanDefinitionException}示例
 */
public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类{Configuration.class}
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        try {
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf(" Spring 应用上下文存在%d个 %s 类型的 Bean,具体原因：%s",
                    e.getNumberOfBeansFound(),
                    String.class.getName(),
                    e.getMessage());
        }
        applicationContext.close();
    }

    @Bean
    private String bean1() {
        return "1";
    }

    @Bean
    private String bean2() {
        return "2";
    }

    @Bean
    private String bean3() {
        return "3";
    }
}
