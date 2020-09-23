package com.huajie.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring 注解属性覆盖示例
 *
 * @author ：xwf
 * @date ：Created in 2020-9-23 23:32
 */
@MyComponentScan2(scanBasePackages = "com.huajie.thinking.in.spring.annotation")
public class AttributeOverridesDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(AttributeOverridesDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();

        System.out.println(context.getBean(TestClass.class));
        System.out.println(context.getBean(TestClass2.class));

        // 关闭 Spring 应用上下文
        context.close();
    }
}
