package com.huajie.thinking.in.spring.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import javax.tools.JavaFileObject;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * 动态（更新）资源 {@link MessageSource} 实现
 * <p>
 * 实现步骤
 * 1.定位资源位置（properties 文件）
 * 2.初始化 Properties 对象
 * 3.实现 AbstractMessageSource#resolveCode
 * 4.监听资源文件（Java NIO 2 WatchService）
 * 5.线程池处理文件变化
 * 6.重新装载 Properties 对象
 *
 * @see MessageSource
 * @see AbstractMessageSource
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private static final String resourceFileName = "msg.properties";

    private static final String resourcePath = "/META-INF/msg.properties";

    private static final String ENCODING = "gbk";

    private final Resource messagePropertiesResource;

    private final Properties messageProperties;

    private ResourceLoader resourceLoader;

    private final ExecutorService executorService;

    public DynamicResourceMessageSource() {
        this.messagePropertiesResource = getMessagePropertiesResource();
        this.messageProperties = loadMessageProperties();
        this.executorService = Executors.newSingleThreadExecutor();
        // 监听资源文件 Java NIO 2 WatchService）
        onMessagePropertiesChanged();
    }

    private void onMessagePropertiesChanged() {
        if (this.messagePropertiesResource.isFile()) {
            try {
                File messagePropertiesFile = this.messagePropertiesResource.getFile();
                Path messagePropertiesFilePath = messagePropertiesFile.toPath();
                // 获取当前 OS 文件系统
                FileSystem fileSystem = FileSystems.getDefault();
                WatchService watchService = fileSystem.newWatchService();
                // 获取上级目录
                Path driPath = messagePropertiesFilePath.getParent();
                // 注册 WatchService 到 driPath
                driPath.register(watchService, ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE);
                // 处理资源文件变化
                ProcessMessagePropertiesChanged(watchService);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 处理资源文件变化
     *
     * @param watchService
     */
    private void ProcessMessagePropertiesChanged(WatchService watchService) {
        executorService.submit(() -> {
            while (true) {
                WatchKey watchKey = null;
                try {
                    watchKey = watchService.take();
                    if (watchKey.isValid()) {
                        Watchable watchable = watchKey.watchable();
                        // 目录路径（监听的注册目录）
                        Path dirPath = (Path) watchable;
                        for (WatchEvent event : watchKey.pollEvents()) {
                            // 事件关联的对象即注册目录的子文件（或者目录）
                            // 事件发生源是相对路径
                            Path fileRelativePath = (Path) event.context();
                            if (resourceFileName.equals(fileRelativePath.getFileName().toString())) {
                                // 处理为绝对路径
                                Path filePath = dirPath.resolve(fileRelativePath);
                                File file = filePath.toFile();
                                Properties properties = loadMessageProperties(new FileReader(file));
                                synchronized (this.messageProperties) {
                                    this.messageProperties.clear();
                                    this.messageProperties.putAll(properties);
                                }
                            }
                        }
                    }
                } finally {
                    if (watchKey != null) {
                        watchKey.reset();
                    }
                }
            }
        });
    }

    private Resource getMessagePropertiesResource() {
        return getResourceLoader().getResource(resourcePath);
    }

    private Properties loadMessageProperties() {
        EncodedResource encodedResource = new EncodedResource(this.messagePropertiesResource, ENCODING);
        try {
            return loadMessageProperties(encodedResource.getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Properties loadMessageProperties(Reader reader) {
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPatten = messageProperties.getProperty(code);
        if (StringUtils.hasText(messageFormatPatten)) {
            return new MessageFormat(messageFormatPatten, locale);
        }
        return null;
    }

    public ResourceLoader getResourceLoader() {
        return this.resourceLoader != null ? this.resourceLoader : new DefaultResourceLoader();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static void main(String[] args) throws InterruptedException {
        DynamicResourceMessageSource source = new DynamicResourceMessageSource();
        for (int i = 0; i < 10000; i++) {
            System.out.println(source.getMessage("name", new Object[]{}, Locale.getDefault()));
            Thread.sleep(1000L);
        }
    }
}
