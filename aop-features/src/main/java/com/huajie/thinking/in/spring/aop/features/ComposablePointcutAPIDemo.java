package com.huajie.thinking.in.spring.aop.features;

import com.huajie.thinking.in.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import com.huajie.thinking.in.spring.aop.features.pointcut.EchoServiceEchoMethodPointcut;
import com.huajie.thinking.in.spring.aop.features.pointcut.EchoServicePointcut;
import com.huajie.thinking.in.spring.aop.overview.DefaultEchoService;
import com.huajie.thinking.in.spring.aop.overview.EchoService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 组合 Pointcut 示例
 *
 * @Author: xiewenfeng
 * @Date: 2021/7/5 14:32
 */
public class ComposablePointcutAPIDemo {

  public static void main(String[] args) {
    EchoServiceEchoMethodPointcut echoServiceMethodPointcut = EchoServiceEchoMethodPointcut.INSTANCE;
    EchoServicePointcut echoServicePointcut = new EchoServicePointcut("echo", EchoService.class);

    ComposablePointcut composablePointcut = new ComposablePointcut();

    composablePointcut.intersection(echoServiceMethodPointcut.getClassFilter())
        .intersection(echoServicePointcut.getMethodMatcher());

    // 将 Pointcut 适配成 Advisor
    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
    advisor.setPointcut(composablePointcut);
    advisor.setAdvice(new EchoServiceMethodInterceptor());

    DefaultEchoService echoService = new DefaultEchoService();

    ProxyFactory proxyFactory = new ProxyFactory(echoService);
    proxyFactory.addAdvisor(advisor);
    EchoService proxy = (EchoService) proxyFactory.getProxy();
    System.out.println(proxy.echo("hello,world"));
  }

}
