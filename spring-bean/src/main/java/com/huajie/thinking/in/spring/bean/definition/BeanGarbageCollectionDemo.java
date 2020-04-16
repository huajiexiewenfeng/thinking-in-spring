package com.huajie.thinking.in.spring.bean.definition;

import com.huajie.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 垃圾回收示例
 */
public class BeanGarbageCollectionDemo {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //关闭
        applicationContext.close();
        System.gc();
        Thread.sleep(10000L);
    }
}
