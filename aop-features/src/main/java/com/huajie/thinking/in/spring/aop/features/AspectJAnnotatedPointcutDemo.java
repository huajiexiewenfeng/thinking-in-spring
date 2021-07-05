package com.huajie.thinking.in.spring.aop.features;

import com.huajie.thinking.in.spring.aop.features.aspect.AspectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: xiewenfeng
 * @Date: 2021/6/9 17:29
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectJAnnotatedPointcutDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(AspectJAnnotatedPointcutDemo.class, AspectConfiguration.class);
    context.refresh();

    AspectJAnnotatedPointcutDemo demo = context.getBean(AspectJAnnotatedPointcutDemo.class);
    demo.execute();
    context.close();
  }

  public void execute() {
    System.out.println("execute()...");
  }

}
