package com.huajie.thinking.in.spring.aop.overview;

/**
 * 默认{@link EchoService}实现
 *
 * @author ：xwf
 * @date ：Created in 2020-12-15 23:17
 */
public class DefaultEchoService implements EchoService {
    @Override
    public String echo(String message) {
        return "[Echo] " + message;
    }
}
