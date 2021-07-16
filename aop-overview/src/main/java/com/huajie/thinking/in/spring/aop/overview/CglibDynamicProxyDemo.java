package com.huajie.thinking.in.spring.aop.overview;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * CGLIB 动态代理实现
 *
 * @Author: xiewenfeng
 * @Date: 2021/7/13 17:19
 */
public class CglibDynamicProxyDemo {

  public static void main(String[] args) {
    Enhancer enhancer = new Enhancer();
    // 指定 super class = DefaultEchoService.class
    Class<?> superClass = DefaultEchoService.class;
    enhancer.setSuperclass(superClass);
    // 指定拦截接口
    enhancer.setInterfaces(new Class[]{EchoService.class});
    // 设置回调
    enhancer.setCallback(new MethodInterceptor() {
      @Override
      public Object intercept(Object source, Method method, Object[] args, MethodProxy methodProxy)
          throws Throwable {
        long startTime = System.currentTimeMillis();
        // Source -> CGLIB 子类
        // 目标类  -> DefaultEchoService
        // 错误使用
//                Object result = method.invoke(source, args);
        // 正确的方法调用
        Object result = methodProxy.invokeSuper(source, args);
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("[CGLIB 字节码提升] echo 方法执行的实现：" + costTime + " ms.");
        return result;
      }
    });
    // 创建代理对象
    EchoService echoService = (EchoService)enhancer.create();
    System.out.println(echoService.echo("Hello,World"));
  }

}
