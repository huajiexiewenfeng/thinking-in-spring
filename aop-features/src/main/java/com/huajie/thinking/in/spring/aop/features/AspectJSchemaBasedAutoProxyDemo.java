package com.huajie.thinking.in.spring.aop.features;

import com.huajie.thinking.in.spring.aop.overview.EchoService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于 XML 配置的 AutoProxy 示例
 *
 * @Author: xiewenfeng
 * @Date: 2021/6/9 17:29
 */
@Configuration
public class AspectJSchemaBasedAutoProxyDemo {

  public static void main(String[] args) {
    String configLocation = "classpath:/META-INF/spring-aop-auto-proxy-context.xml";
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
    context.refresh();

    EchoService echoService = context.getBean("echoService", EchoService.class);
    System.out.println(echoService.echo("hello.world"));
    context.close();
  }

}
