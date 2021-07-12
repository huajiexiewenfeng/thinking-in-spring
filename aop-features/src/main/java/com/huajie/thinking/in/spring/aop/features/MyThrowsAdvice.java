package com.huajie.thinking.in.spring.aop.features;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.springframework.aop.ThrowsAdvice;

/**
 * @Author: xiewenfeng
 * @Date: 2021/7/7 14:17
 */
public class MyThrowsAdvice implements ThrowsAdvice {

  public static final MyThrowsAdvice INSTANCE = new MyThrowsAdvice();

  private MyThrowsAdvice(){

  }

  public void afterThrowing(Exception ex) {
    System.out.printf("Exception : %s",
        ex);
  }

  public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
    System.out.printf("Method : %s, args : %s, target : %s ,exception : %s",
        method,
        Arrays.asList(args),
        target,
        ex);
  }

}
