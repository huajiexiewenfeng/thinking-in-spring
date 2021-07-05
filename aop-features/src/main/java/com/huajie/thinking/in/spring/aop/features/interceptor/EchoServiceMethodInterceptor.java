package com.huajie.thinking.in.spring.aop.features.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author: xiewenfeng
 * @Date: 2021/6/9 15:51
 */
public class EchoServiceMethodInterceptor implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    String methodName = invocation.getMethod().getName();
    System.out.printf("拦截 EchoService 方法:%s\n", methodName);
    return invocation.proceed();
  }
}
