package com.huajie.thinking.in.spring.event;

import org.springframework.context.support.GenericApplicationContext;

/**
 * 自定义 Spring 事件示例
 *
 * @author ：xwf
 * @date ：Created in 2020-6-19 17:11
 */
public class CustomizedSpringEventDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        // 1.添加自定义 Spring 事件监听器
        context.addApplicationListener(new MyApplicationListener());

        // 2.启动 Spring 应用上下文
        context.refresh();

        // 3.发布自定义 Spring 事件
        context.publishEvent(new MySpringEvent("hello,world"));

        // 4.关闭应用上下文
        context.close();
    }
}
