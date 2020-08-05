package com.huajie.thinking.in.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 异步事件处理示例-基于{@link Async}注解
 *
 * @author ：xwf
 * @date ：Created in 2020\6\26 0026 7:42
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 1.注册当前类作为 Configuration Class
        context.register(AnnotatedAsyncEventHandlerDemo.class);

        // 2.启动 Spring 应用上下文
        context.refresh();

        // 3.发布自定义 Spring 事件
        context.publishEvent(new MySpringEvent("hello,world"));

        // 4.关闭应用上下文
        context.close();
    }

    @EventListener
    @Async
    public void onApplicationEvent(MySpringEvent event) {
        System.out.println(String.format("基于 @EventListener - 接收到 Spring 事件：[线程：%s]:%s\n",
                Thread.currentThread().getName(), event));
    }

    @Bean
    public Executor taskExecutor(){
        return Executors.newSingleThreadExecutor(
                new CustomizableThreadFactory("my-spring-event-thread-pool")
        );
    }
}
