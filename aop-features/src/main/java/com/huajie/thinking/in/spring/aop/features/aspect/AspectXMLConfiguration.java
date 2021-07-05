package com.huajie.thinking.in.spring.aop.features.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect 配置类
 *
 * @Author: xiewenfeng
 * @Date: 2021/5/27 17:40
 */
public class AspectXMLConfiguration {

  private void beforeMethod() {
    System.out.println("@Before any public method.");
  }

}
