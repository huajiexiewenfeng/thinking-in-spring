package com.huajie.thinking.in.spring.resource.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * {@link Resource} 工具类
 */
public interface ResourceUtils {

    static String getContent(Resource resource, String encoding) {
        EncodedResource encodedResource = new EncodedResource(resource, encoding);
        // 自动关闭资源
        try (Reader reader = encodedResource.getReader()) {
            return IOUtils.toString(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String getContent(Resource resource) {
        return getContent(resource, "UTF-8");
    }

}
