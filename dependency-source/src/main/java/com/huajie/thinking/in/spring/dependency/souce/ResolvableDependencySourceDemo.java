package com.huajie.thinking.in.spring.dependency.souce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency 作为依赖来源
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String name;

    @PostConstruct
    public void init(){
        System.out.println(name);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableDependencySourceDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class,"xwf-post");
        });
        applicationContext.refresh();

         //如果采用这种方式，需要将register放到refresh之后 原因和 refresh 中代码的spring应用上下文启动过程有关
//        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
//        if (beanFactory instanceof ConfigurableListableBeanFactory) {
//            ConfigurableListableBeanFactory configurableListableBeanFactory = (ConfigurableListableBeanFactory) beanFactory;
//            configurableListableBeanFactory.registerResolvableDependency(String.class,"xwf");
//
//        }

        ResolvableDependencySourceDemo bean = applicationContext.getBean(ResolvableDependencySourceDemo.class);
        System.out.println(bean.name);

        applicationContext.close();
    }
}
