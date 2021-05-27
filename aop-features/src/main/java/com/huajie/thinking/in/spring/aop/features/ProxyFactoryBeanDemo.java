package com.huajie.thinking.in.spring.aop.features;

import com.huajie.thinking.in.spring.aop.overview.EchoService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: xiewenfeng
 * @Date: 2021/5/27 18:03
 */
@Aspect
@Configuration
public class ProxyFactoryBeanDemo {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        "classpath:/META-INF/spring-aop-context.xml");
    EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);
    System.out.println(echoService.echo("hello,world"));
    context.close();
  }

}
