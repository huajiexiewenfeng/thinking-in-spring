package com.huajie.thinking.in.spring.aop.features.aspect;

import java.util.Random;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * Aspect 配置类
 *
 * @Author: xiewenfeng
 * @Date: 2021/5/27 17:40
 */
@Aspect
@Order
public class AspectConfiguration {

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
  private void beforeMethod() throws Throwable {
    Random random = new Random();
    if (random.nextBoolean()) {
      throw new RuntimeException("for purpose.");
    }
    System.out.println("@Before any public method.");
  }

  @Around("anyPublicMethod()")
  public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("@Around any public method.");
    // 需要主动调用方法
    return pjp.proceed();
  }

  @AfterReturning("anyPublicMethod()")
  private void afterAnyMethod() {
    System.out.println("@AfterReturning any public method.");
  }

  @AfterThrowing("anyPublicMethod()")
  private void afterThrowingAnyMethod() {
    System.out.println("@AfterThrowing any public method.");
  }

  @After("anyPublicMethod()")
  private void finalizeAnyMethod() {
    System.out.println("@After any public method.");
  }

}
