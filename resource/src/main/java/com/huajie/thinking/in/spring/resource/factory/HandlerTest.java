package com.huajie.thinking.in.spring.resource.factory;

import org.apache.commons.io.IOUtils;
import sun.net.www.protocol.f.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * F Handler 测试示例
 */
public class HandlerTest {
    public static void main(String[] args) throws IOException {
        // 这里的 x: 对应的是 sun.net.www.protocol.x 中的 x ; 表示 x协议
        URL url = new URL("f:///META-INF/default.properties");
        Handler handler = (Handler) new HandlerFactory().createURLStreamHandler("f");
        URLConnection urlConnection = handler.openConnection(url);
        InputStream inputStream = urlConnection.getInputStream();
        String s = IOUtils.toString(inputStream);
        System.out.println(s);
    }
}
