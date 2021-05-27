package com.huajie.thinking.in.spring.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: xiewenfeng
 * @Date: 2021/4/8 18:22
 */
@Configuration
@Aspect // 声明为一个切面
public class AspectJXMLDemo {

  public static void main(String[] args) {
    String configLocation = "classpath:/META-INF/spring-aop-context.xml";
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);

    AspectJXMLDemo demo = context.getBean(AspectJXMLDemo.class);

    context.close();
  }

}
