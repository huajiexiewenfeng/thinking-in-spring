package com.huajie.thinking.in.spring.aop.features;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于 XML 配置的 PointCut 示例
 *
 * @Author: xiewenfeng
 * @Date: 2021/6/9 17:29
 */
@Configuration
public class AspectJSchemaBasedPointcutDemo {

  public static void main(String[] args) {
    String configLocation = "classpath:/META-INF/spring-aop-context.xml";
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
    context.refresh();

    AspectJSchemaBasedPointcutDemo demo = context.getBean(AspectJSchemaBasedPointcutDemo.class);
    demo.execute();
    context.close();
  }

  public void execute() {
    System.out.println("execute()...");
  }

}
