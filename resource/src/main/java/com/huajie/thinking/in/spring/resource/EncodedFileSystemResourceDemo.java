package com.huajie.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StreamUtils;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * 带有字符编码 {@link FileSystemResource} 示例
 *
 * @see FileSystemResource
 * @see EncodedResource
 */
public class EncodedFileSystemResourceDemo {
    public static void main(String[] args) throws IOException {
        String projectPath = "\\resource\\src\\main\\java\\com\\huajie\\thinking\\in\\spring\\resource";
        String currentJavaFilePath = System.getProperty("user.dir") + projectPath + "\\EncodedFileSystemResourceDemo.java";
        System.out.println(currentJavaFilePath);
        File currentJavaFile = new File(currentJavaFilePath);
        // FileSystemResource -> WritableResource -> Resource
        FileSystemResource fileSystemResource = new FileSystemResource(currentJavaFile);

        EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");
        // 自动关闭资源
        try (Reader reader = encodedResource.getReader()) {
            String s = IOUtils.toString(reader);
            System.out.println(s);
        }
    }
}
