package com.huajie.thinking.in.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 依赖注入 {@link ApplicationEventPublisher} 示例
 *
 * @author ：xwf
 * @date ：Created in 2020\6\25 0025 15:31
 */
public class InjectionApplicationEventPublisherDemo implements ApplicationEventPublisherAware, ApplicationContextAware {

    @Autowired
    private ApplicationEventPublisher autowiredApplicationEventPublisher;

    @Autowired
    private ApplicationContext autowiredApplicationContext;

    @PostConstruct
    public void init() {
        // #3
        autowiredApplicationEventPublisher.publishEvent(new MySpringEvent("the event from @Autowired-ApplicationEventPublisher"));
        // #4
        autowiredApplicationContext.publishEvent(new MySpringEvent("the event from @Autowired-ApplicationContext"));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(InjectionApplicationEventPublisherDemo.class);

        //添加 Spring 事件监听
        context.addApplicationListener(new MyApplicationListener());

        context.refresh();

        context.close();
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new MySpringEvent("the event from ApplicationEventPublisherAware"));// #1
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.publishEvent(new MySpringEvent("the event from ApplicationContextAware"));// #2
    }
}
