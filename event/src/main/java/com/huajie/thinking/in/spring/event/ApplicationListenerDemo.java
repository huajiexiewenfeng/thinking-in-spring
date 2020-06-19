package com.huajie.thinking.in.spring.event;

import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.*;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link ApplicationListener} 示例
 *
 * @author ：xwf
 * @date ：Created in 2020\6\18 0018 20:52
 */
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {
    public static void main(String[] args) {
//        GenericApplicationContext context = new GenericApplicationContext();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ApplicationListenerDemo.class);

        // 基于 接口 向 Spring 应用上下文中注册时间
        // 方法一：通过 ConfigurableApplicationContext API 注册
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("ApplicationListener - 接收到 Spring 事件：" + event);
            }
        });

        // 方法二：基于 ApplicationListener 注册为 Spring Bean
        context.register(MyApplicationListener.class);

        context.refresh();
        context.start();
        context.stop();
        context.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("hello,world"){

        });
        // 改造成 PayLoadApplicationEvent
        applicationEventPublisher.publishEvent("hello,world");
        // 发送 PayLoadApplicationEvent
        applicationEventPublisher.publishEvent(new PayloadApplicationEvent(this,"hello,world"));
        // 发送 PayLoadApplicationEvent
        applicationEventPublisher.publishEvent(new MyPayLoadApplicationEvent(this,"hello,world"));
    }

    static class MyPayLoadApplicationEvent<String> extends PayloadApplicationEvent<String>{

        /**
         * Create a new PayloadApplicationEvent.
         *
         * @param source  the object on which the event initially occurred (never {@code null})
         * @param payload the payload object (never {@code null})
         */
        public MyPayLoadApplicationEvent(Object source, String payload) {
            super(source, payload);
        }
    }

    /**
     * 这里的泛型设计可以指定具体类型，或者父类
     */
   public static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent>{

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println("MyApplicationListener - 接收到 Spring 事件：" + event);
        }
    }

    @EventListener
    public void onApplicationEvent(ApplicationEvent event){
        System.out.println("@EventListener - 接收到 Spring 事件：" + event);
    }

    @EventListener
    @Order(2)
    public void onApplicationEvent(ContextRefreshedEvent event){
        System.out.println("@EventListener(onApplicationEvent) - 接收到 [ContextRefreshedEvent] 事件：" + event);
    }

    @EventListener
    @Order(1)
    public void onApplicationEvent1(ContextRefreshedEvent event){
        System.out.println("@EventListener(onApplicationEvent1) - 接收到 [ContextRefreshedEvent] 事件：" + event);
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event){
        System.out.println("@EventListener - 接收到 [ContextStartedEvent] 事件：" + event);
    }

    @EventListener
    @Async
    public void onApplicationEvent(ContextStoppedEvent event){
        System.out.println("@EventListener(Async 异步) - 接收到 [ContextStoppedEvent] 事件：" + event);
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event){
        System.out.println("@EventListener - 接收到 [ContextClosedEvent] 事件：" + event);
    }

}
