package com.huajie.thinking.in.spring.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: xiewenfeng
 * @Date: 2021/4/8 18:22
 */
@Configuration
@Aspect // 声明为一个切面
@EnableAspectJAutoProxy // 激活 Aspect 注解自动代理
public class AspectJAnnotationDemo {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(AspectJAnnotationDemo.class);
    context.refresh();

    AspectJAnnotationDemo demo = context.getBean(AspectJAnnotationDemo.class);

    context.close();
  }

}
