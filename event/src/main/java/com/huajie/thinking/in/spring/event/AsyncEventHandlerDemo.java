package com.huajie.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步事件处理示例
 *
 * @author ：xwf
 * @date ：Created in 2020\6\26 0026 7:42
 */
public class AsyncEventHandlerDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        // 1.添加自定义 Spring 事件监听器
        context.addApplicationListener(new MyApplicationListener());

        // 2.启动 Spring 应用上下文
        context.refresh();

        // 依赖查找 ApplicationEventMulticaster

        ApplicationEventMulticaster applicationEventMulticaster = context.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME,
                ApplicationEventMulticaster.class);
        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;
            ExecutorService taskExecutor = Executors.newSingleThreadExecutor(
                    new CustomizableThreadFactory("my-spring-event-thread-pool")
            );
            // 同步执行 -> 异步执行
            simpleApplicationEventMulticaster.setTaskExecutor(taskExecutor);
            // 添加应用上下文关闭的 listener 监听事件 关闭线程池
            applicationEventMulticaster.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
                @Override
                public void onApplicationEvent(ContextClosedEvent event) {
                    if (!taskExecutor.isShutdown()) {
                        taskExecutor.shutdown();
                    }
                }
            });

            simpleApplicationEventMulticaster.setErrorHandler(e -> {
                System.out.println("当 Spring 事件异常时，原因：" + e.getMessage());
            });
        }

        // 故意抛出异常
        context.addApplicationListener(new ApplicationListener<MySpringEvent>() {
            @Override
            public void onApplicationEvent(MySpringEvent event) {
                throw new RuntimeException("故意抛出异常");
            }
        });

        // 3.发布自定义 Spring 事件
        context.publishEvent(new MySpringEvent("hello,world"));

        // 4.关闭应用上下文
        context.close();
    }
}
