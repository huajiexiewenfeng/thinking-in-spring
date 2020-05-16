package sun.net.www.protocol.x;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * X Handler 测试示例
 */
public class HandlerTest {
    public static void main(String[] args) throws IOException {
        // 这里的 x: 对应的是 sun.net.www.protocol.x 中的 x ; 表示 x协议
        URL url = new URL("x:///META-INF/default.properties");
        InputStream inputStream = url.openStream();
        String s = IOUtils.toString(inputStream);
        System.out.println(s);
    }
}
