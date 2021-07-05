package com.huajie.thinking.in.spring.aop.features;

import com.huajie.thinking.in.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.huajie.thinking.in.spring.aop.overview.DefaultEchoService;
import com.huajie.thinking.in.spring.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @Author: xiewenfeng
 * @Date: 2021/6/9 16:26
 */
public class ProxyFactoryDemo {

  public static void main(String[] args) {
    DefaultEchoService echoService = new DefaultEchoService();
    // 注入目标对象（被代理）
    ProxyFactory proxyFactory = new ProxyFactory(echoService);
    // 添加方法拦截
    proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
    // 获取代理对象
    EchoService proxy = (EchoService) proxyFactory.getProxy();
    System.out.println(proxy.echo("hello,world"));
  }

}
