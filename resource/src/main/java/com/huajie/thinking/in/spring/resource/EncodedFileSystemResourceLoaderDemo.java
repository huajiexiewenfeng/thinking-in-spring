package com.huajie.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * 带有字符编码 {@link FileSystemResourceLoader} 示例
 *
 * @see FileSystemResourceLoader
 * @see FileSystemResource
 * @see EncodedResource
 */
public class EncodedFileSystemResourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        String projectPath = "\\resource\\src\\main\\java\\com\\huajie\\thinking\\in\\spring\\resource";
        String currentJavaFilePath = System.getProperty("user.dir") + projectPath + "\\EncodedFileSystemResourceLoaderDemo.java";
        System.out.println(currentJavaFilePath);
        File currentJavaFile = new File(currentJavaFilePath);

        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource(currentJavaFilePath);

        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        try (Reader reader = encodedResource.getReader()) {
            String s = IOUtils.toString(reader);
            System.out.println(s);
        }
    }
}
