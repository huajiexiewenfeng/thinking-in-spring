package com.huajie.thinking.in.spring.aop.overview;

/**
 * @author ：xwf
 * @date ：Created in 2020-12-15 23:15
 */
public class StaticProxyDemo {
    public static void main(String[] args) {
        // 实现效果，打印方法的执行时间
        // 一般做法
        normalMethod();
        // 静态代理
        staticProxyMethod();
    }

    /**
     * 打印方法的执行时间，一般做法在方法前后打印，或者在 EchoService 中的 echo 方法前后执行
     */
    private static void normalMethod() {
        Long startTime = System.currentTimeMillis();
        EchoService echoService = new DefaultEchoService();
        echoService.echo("hello,world");
        Long endTime = System.currentTimeMillis();
        System.out.println("方法执行耗时" + (endTime - startTime));
    }

    private static void staticProxyMethod() {
        EchoService echoService = new DefaultEchoService();
        ProxyEchoService proxy = new ProxyEchoService(echoService);
        proxy.echo("hello,world");
    }
}
