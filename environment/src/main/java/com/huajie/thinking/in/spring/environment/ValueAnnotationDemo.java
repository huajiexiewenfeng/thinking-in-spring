package com.huajie.thinking.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * {@link Value} 注解示例
 *
 * @author ：xwf
 * @date ：Created in 2020\10\7 0007 16:30
 * @see Value
 */
public class ValueAnnotationDemo {

    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ValueAnnotationDemo.class);
        // 启动 spring 应用上下文
        context.refresh();
        ValueAnnotationDemo demo =  context.getBean(ValueAnnotationDemo.class);
        System.out.println(demo.userName);
        // 关闭 spring 应用上下文
        context.close();
    }
}
