package com.huajie.thinking.in.spring.i18n;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Spring Boot 场景下定义 {@link MessageSource}
 *
 * @see MessageSource
 * @see MessageSourceAutoConfiguration
 * @see ReloadableResourceBundleMessageSource
 */
@EnableAutoConfiguration
public class CustomizedMessageSourceBeanDemo {

    /**
     * 在 Spring Boot 场景中，Primary Configuration Sources(Classes) 高于 *AutoConfiguration
     * @return
     */
//    @Bean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    public MessageSource messageSource(){
        return new ReloadableResourceBundleMessageSource();
    }
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CustomizedMessageSourceBeanDemo.class, args);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if (beanFactory.containsLocalBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)) {
            MessageSource messageSource = applicationContext.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
            System.out.println(messageSource);
        }
        applicationContext.close();
    }
}
