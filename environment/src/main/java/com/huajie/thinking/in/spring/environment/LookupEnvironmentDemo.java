package com.huajie.thinking.in.spring.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;


/**
 * 依赖查找 {@link Environment}
 *
 * @author ：xwf
 * @date ：Created in 2020\10\6 0006 17:15
 * @see Environment
 */
public class LookupEnvironmentDemo {

    @Autowired
    private Environment environmentAutowired;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LookupEnvironmentDemo.class);
        // 启动 spring 应用上下文
        context.refresh();
        LookupEnvironmentDemo demo =  context.getBean(LookupEnvironmentDemo.class);
        // 通过 Environment Bean 的名称来进行依赖查找
        Environment environment = context.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);
        Environment environment2 = context.getEnvironment();
        System.out.println(environment == environment2);
        System.out.println(environment == demo.environmentAutowired);
        // 关闭 spring 应用上下文
        context.close();
    }

}
