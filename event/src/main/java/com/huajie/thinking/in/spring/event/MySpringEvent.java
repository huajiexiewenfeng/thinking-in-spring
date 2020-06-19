package com.huajie.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义 Spring 事件
 *
 * @author ：xwf
 * @date ：Created in 2020-6-19 17:15
 */
public class MySpringEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param message the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MySpringEvent(String message) {
        super(message);
    }

    @Override
    public Object getSource() {
        return String.valueOf(super.getSource());
    }

    public Object getMessage() {
        return getSource();
    }

}
