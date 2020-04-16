package com.huajie.thinking.in.spring.dependency.injection;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 API 实现 Setter 注入示例
 */
public class ApiDependencySetterInjectDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ApiDependencySetterInjectDemo.class);

        applicationContext.registerBeanDefinition("user-holder",createUserBean());
        applicationContext.refresh();


        UserHolder bean = applicationContext.getBean(UserHolder.class);
        System.out.println(bean);
        applicationContext.close();
    }

    /**
     * 为 {@link UserHolder} 生成 {@link BeanDefinition}
     *
     */
    private static BeanDefinition createUserBean() {
        //1.通过 BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        //通过属性设置
        beanDefinitionBuilder.addPropertyReference("user","user");
        // 获取 BeanDefinition 对象
        return beanDefinitionBuilder.getBeanDefinition();
    }


    @Bean
    public User user(){
        User user = new User();
        user.setName("xwf-api");
        user.setAge(20);
        return user;
    }

}
