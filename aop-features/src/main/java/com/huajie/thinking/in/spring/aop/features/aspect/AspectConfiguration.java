package com.huajie.thinking.in.spring.aop.features.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Aspect 配置类
 *
 * @Author: xiewenfeng
 * @Date: 2021/5/27 17:40
 */
@Aspect
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
  private void beforeMethod() {
    System.out.println("@Before any public method.");
  }

  @Around("anyPublicMethod()")
  public Object aroundMethod(ProceedingJoinPoint pjp) {
    System.out.println("@Around any public method.");
    try {
      // 需要主动调用方法
      return pjp.proceed();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return null;
  }


}
