package com.huajie.thinking.in.spring.resource.springx;

import org.apache.commons.io.IOUtils;
import java.io.InputStream;
import java.net.URL;

/**
 * 简单的继承 {@link Handler}
 */
public class Handler extends sun.net.www.protocol.x.Handler {
    // -Djava.protocol.handler.pkgs=com.huajie.thinking.in.spring.resource
    public static void main(String[] args) throws Exception {
        URL url = new URL("springx:///META-INF/default.properties");
        InputStream inputStream = url.openStream();
        String s = IOUtils.toString(inputStream);
        System.out.println(s);
    }
}
