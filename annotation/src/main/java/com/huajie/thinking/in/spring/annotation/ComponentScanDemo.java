package com.huajie.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * {@link Component} 扫描示例
 *
 * @author ：xwf
 * @date ：Created in 2020\7\11 0011 13:12
 */
@ComponentScan(basePackages = "com.huajie.thinking.in.spring.annotation")
public class ComponentScanDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(ComponentScanDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();

        System.out.println(context.getBean(TestClass.class));
        System.out.println(context.getBean(TestClass2.class));

        // 关闭 Spring 应用上下文
        context.close();
    }
}
