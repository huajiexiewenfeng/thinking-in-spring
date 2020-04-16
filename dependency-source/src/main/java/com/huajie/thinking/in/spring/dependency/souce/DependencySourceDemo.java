package com.huajie.thinking.in.spring.dependency.souce;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源示例
 */
public class DependencySourceDemo {

    /**
     * 注入在 postProcessProperties 方法执行，早于 setter 注入 ，也早于 @PostConstruct
     */
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init() {
        System.out.println("beanFactory == applicationContext：" + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory：" + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext：" + (resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext：" + (applicationEventPublisher == applicationContext));
    }

    @PostConstruct
    public void initLookup() {
        getBean(BeanFactory.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationContext.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.out.println("当前类型：" + beanType + " 无法在 BeanFactory 中查找！");
        }
        return null;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);

        applicationContext.refresh();

        DependencySourceDemo demo = applicationContext.getBean(DependencySourceDemo.class);
        System.out.println(demo.beanFactory);
        System.out.println(demo.resourceLoader);
        System.out.println(demo.applicationContext);
        System.out.println(demo.applicationEventPublisher);
        applicationContext.close();
    }

}
