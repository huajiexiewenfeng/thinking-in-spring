package sun.net.www.protocol.f;

import sun.net.www.protocol.x.XURLConnection;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler {

    @Override
    public URLConnection openConnection(URL u) throws IOException {
        return new XURLConnection(u);
    }


}
