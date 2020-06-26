package com.huajie.thinking.in.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * {@link MySpringEvent} 事件监听器
 *
 * @author ：xwf
 * @date ：Created in 2020-6-19 17:19
 */
public class MyApplicationListener implements ApplicationListener<MySpringEvent> {

    @Override
    public void onApplicationEvent(MySpringEvent event) {
        System.out.println(String.format("MyApplicationListener - 接收到 Spring 事件：[线程：%s]:%s\n",
                Thread.currentThread().getName(), event));
    }
}
