package com.huajie.thinking.in.spring.aop.features;

import com.huajie.thinking.in.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.huajie.thinking.in.spring.aop.features.pointcut.EchoServicePointcut;
import com.huajie.thinking.in.spring.aop.overview.DefaultEchoService;
import com.huajie.thinking.in.spring.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @Author: xiewenfeng
 * @Date: 2021/7/5 14:32
 */
public class PointcutAPIDemo {

  public static void main(String[] args) {
    EchoServicePointcut echoServicePointcut = new EchoServicePointcut("echo", EchoService.class);
    // 将 Pointcut 适配成 Advisor
    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
    advisor.setPointcut(echoServicePointcut);
    advisor.setAdvice(new EchoServiceMethodInterceptor());

    DefaultEchoService echoService = new DefaultEchoService();

    ProxyFactory proxyFactory = new ProxyFactory(echoService);
    proxyFactory.addAdvisor(advisor);
    EchoService proxy = (EchoService) proxyFactory.getProxy();
    System.out.println(proxy.echo("hello,world"));
  }

}
