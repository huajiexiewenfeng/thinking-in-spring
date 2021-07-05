package com.huajie.thinking.in.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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

  public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("@Around any public method ->" + pjp.getSignature());
    // 需要主动调用方法
    return pjp.proceed();
  }

  private void afterReturnAnyMethod() {
    System.out.println("@AfterReturning any public method.");
  }

  private void afterThrowingAnyMethod() {
    System.out.println("@AfterThrowing any public method.");
  }

  private void finalizeAnyMethod() {
    System.out.println("@After any public method.");
  }

}
