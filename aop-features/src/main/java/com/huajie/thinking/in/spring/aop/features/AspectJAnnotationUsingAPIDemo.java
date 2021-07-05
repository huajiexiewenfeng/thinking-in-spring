package com.huajie.thinking.in.spring.aop.features;

import com.huajie.thinking.in.spring.aop.features.aspect.AspectConfiguration;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: xiewenfeng
 * @Date: 2021/4/8 18:22
 */
public class AspectJAnnotationUsingAPIDemo {

  public static void main(String[] args) {
    // 通过创建一个 HashMap 缓存,作为被代理对象
    Map<String, Object> cache = new HashMap<>();
    // 创建 Proxy 工厂
    AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);
    // 增加 Aspect 配置类
    proxyFactory.addAspect(AspectConfiguration.class);
    // 增加通知
    proxyFactory.addAdvice(new MethodBeforeAdvice() {
      @Override
      public void before(Method method, Object[] args, Object target) throws Throwable {
        if ("put".equals(method.getName()) && args.length == 2) {
          System.out.printf("before->当前存放 key:%s,value:%s \n", args[0], args[1]);
        }
      }
    });

    // 增加 afterReturnAdvice
    proxyFactory.addAdvice(new AfterReturningAdvice() {
      @Override
      public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
          throws Throwable {
        if ("put".equals(method.getName()) && args.length == 2) {
          System.out.printf("after->当前存放 key:%s,value:%s,之前关联的 value:%s \n", args[0], args[1],
              returnValue);
        }
      }
    });

    Map<String, Object> proxy = proxyFactory.getProxy();
    proxy.put("1", "xwf");
    proxy.put("1", "B");
  }

}
