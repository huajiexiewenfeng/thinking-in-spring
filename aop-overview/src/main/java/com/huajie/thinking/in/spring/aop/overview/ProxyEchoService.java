package com.huajie.thinking.in.spring.aop.overview;

/**
 * @author ：xwf
 * @date ：Created in 2020-12-15 23:22
 */
public class ProxyEchoService implements EchoService {

    private EchoService echoService;

    public ProxyEchoService(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public String echo(String message) {
        Long startTime = System.currentTimeMillis();
        String result = echoService.echo(message);
        Long endTime = System.currentTimeMillis();
        System.out.println("方法执行耗时" + (endTime - startTime));
        return result;
    }
}
