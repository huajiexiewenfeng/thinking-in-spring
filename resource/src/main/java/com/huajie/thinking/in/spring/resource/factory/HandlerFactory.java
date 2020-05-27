package com.huajie.thinking.in.spring.resource.factory;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

/**
 * F HandlerFactory 测试示例
 */
public class HandlerFactory implements URLStreamHandlerFactory {

    private static String PREFIX = "sun.net.www.protocol";

    public URLStreamHandler createURLStreamHandler(String protocol) {
        String className = PREFIX + "." + protocol + ".Handler";
        try {
            Class clazz = Class.forName(className);
            return (URLStreamHandler) clazz.newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
