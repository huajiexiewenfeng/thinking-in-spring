package com.huajie.thinking.in.spring.bean.lifecycle;

import com.huajie.thinking.in.spring.bean.lifecycle.domain.UserHolder;
import com.huajie.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Bean 初始化生命周期
 */
public class BeanInitializationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加 BeanpostBeanProcess 实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcess());
        //添加 CommonAnnotationBeanPostProcessor 触发 @PostConstruct
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"classpath:/META-INF/dependency-lookup-context.xml", "classpath:/META-INF/bean-constructor-injection.xml"};
        int count = reader.loadBeanDefinitions(locations);

        beanFactory.preInstantiateSingletons();

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);

        System.out.println(userHolder);
    }

}
