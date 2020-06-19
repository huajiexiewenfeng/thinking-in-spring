package com.huajie.thinking.in.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 层次性 Spring 事件传播示例
 *
 * @author ：xwf
 * @date ：Created in 2020-6-19 14:34
 */
public class HierarchicalSpringEventPropagateDemo {

    public static void main(String[] args) {
        // 1.创建 parent Spring 应用上下文
        AnnotationConfigApplicationContext parentApplicationContext = new AnnotationConfigApplicationContext();
        parentApplicationContext.setId("parent-context");
        // 2.创建 current Spring 应用上下文
        AnnotationConfigApplicationContext currentApplicationContext = new AnnotationConfigApplicationContext();
        currentApplicationContext.setId("current-context");
        // 3.current -> parent
        currentApplicationContext.setParent(parentApplicationContext);

        // 将 MyListener 注册应用上下文
//        currentApplicationContext.addApplicationListener(new MyListener());
        parentApplicationContext.register(MyListener.class);
        currentApplicationContext.register(MyListener.class);

        // 启动应用上下文
        parentApplicationContext.refresh();
        currentApplicationContext.refresh();
    }

    static class MyListener implements ApplicationListener<ContextRefreshedEvent> {

        private static Set<ContextRefreshedEvent> processedEvents = new LinkedHashSet<>();

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            if(processedEvents.add(event)){
                ApplicationContext applicationContext = event.getApplicationContext();
                System.out.printf("上下文[id：%s]接收到事件:[%s]\n", applicationContext.getId(), event);
            }
        }
    }

}
