package com.huajie.thinking.in.spring.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理示例
 *
 * @author ：xwf
 * @date ：Created in 2020-12-15 23:29
 */
public class JdkDynamicProxyDemo {
    public static void main(String[] args) {

        ClassLoader loader = JdkDynamicProxyDemo.class.getClassLoader();

        EchoService echoService = (EchoService) Proxy.newProxyInstance(loader,
                new Class[]{EchoService.class},
                new InvocationHandlerImpl(new DefaultEchoService())
        );
        echoService.echo("hello,jdk");
    }

    static class InvocationHandlerImpl implements InvocationHandler {

        private Object target;

        public InvocationHandlerImpl(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Long startTime = System.currentTimeMillis();
            Object result = method.invoke(target, args);
            Long endTime = System.currentTimeMillis();
            System.out.println("方法执行耗时" + (endTime - startTime));
            return result;
        }
    }
}
