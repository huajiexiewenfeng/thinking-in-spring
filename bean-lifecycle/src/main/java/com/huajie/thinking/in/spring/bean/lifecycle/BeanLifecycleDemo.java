package com.huajie.thinking.in.spring.bean.lifecycle;

import com.huajie.thinking.in.spring.bean.lifecycle.domain.UserHolder;
import com.huajie.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Bean 完整生命周期
 */
public class BeanLifecycleDemo {
    public static void main(String[] args) throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加 BeanpostBeanProcess 实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcess());
        //添加 DestructionAwareBeanPostProcessor 销毁前
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        //添加 CommonAnnotationBeanPostProcessor 触发 @PostConstruct
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"classpath:/META-INF/dependency-lookup-context.xml", "classpath:/META-INF/bean-constructor-injection.xml"};
        int count = reader.loadBeanDefinitions(locations);

        beanFactory.preInstantiateSingletons();

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
//        beanFactory.destroyBean("userHolder",userHolder);
        beanFactory.destroySingletons();
        System.out.println(userHolder);
        userHolder=null;
        System.gc();
        Thread.sleep(5000);
    }

}
