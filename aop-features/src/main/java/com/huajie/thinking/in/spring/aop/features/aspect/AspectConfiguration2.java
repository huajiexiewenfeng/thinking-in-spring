package com.huajie.thinking.in.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Aspect 配置类
 *
 * @Author: xiewenfeng
 * @Date: 2021/5/27 17:40
 */
@Aspect
@Order(0)
public class AspectConfiguration2 {

  /**
   * 匹配 Join Point
   */
  @Pointcut("execution(public * *(..))")
  private void anyPublicMethod() { // 方法签名
    System.out.println("@Pointcut at any public method.");
  }

  /**
   * Join Point 拦截动作
   */
  @Before("anyPublicMethod()")
  private void beforeMethod() {
    System.out.println("@Before any public method.(2)");
  }

}
