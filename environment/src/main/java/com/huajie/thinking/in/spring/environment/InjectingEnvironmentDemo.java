package com.huajie.thinking.in.spring.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入 {@link Environment}
 * @author ：xwf
 * @date ：Created in 2020\10\6 0006 17:15
 * @see Environment
 */
public class InjectingEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

    @Autowired
    private Environment environmentAutowired;

    private Environment environmentAware;

    @Autowired
    private ApplicationContext applicationContextAutowired;

    private ApplicationContext applicationContextAware;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingEnvironmentDemo.class);
        // 启动 spring 应用上下文
        context.refresh();

        InjectingEnvironmentDemo demo =  context.getBean(InjectingEnvironmentDemo.class);

        System.out.println(demo.environmentAutowired);
        System.out.println(demo.environmentAware==demo.environmentAutowired);
        System.out.println(demo.environmentAware==context.getEnvironment());
        System.out.println(demo.environmentAware==demo.applicationContextAutowired.getEnvironment());
        System.out.println(demo.environmentAware==demo.applicationContextAware.getEnvironment());
        // 关闭 spring 应用上下文
        context.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environmentAware = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContextAware = applicationContext;
    }
}
