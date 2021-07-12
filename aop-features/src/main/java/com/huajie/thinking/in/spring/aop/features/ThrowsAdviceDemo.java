package com.huajie.thinking.in.spring.aop.features;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @Author: xiewenfeng
 * @Date: 2021/7/7 14:06
 */
public class ThrowsAdviceDemo {

  public static void main(String[] args) throws Throwable {
    ThrowsAdviceDemo instance = new ThrowsAdviceDemo();

    ProxyFactory proxyFactory = new ProxyFactory(instance);

    proxyFactory.addAdvice(MyThrowsAdvice.INSTANCE);

    ThrowsAdviceDemo proxy = (ThrowsAdviceDemo) proxyFactory.getProxy();
    proxy.execute();

  }

  public void execute() throws Throwable {
    Random random = new Random();
    if (random.nextBoolean()) {
      throw new RuntimeException("for purpose.");
    }
    System.out.println("Executing...");
  }

}
