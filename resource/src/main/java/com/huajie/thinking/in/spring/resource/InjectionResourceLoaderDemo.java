package com.huajie.thinking.in.spring.resource;

import com.huajie.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * 注入 {@link ResourceLoader} 示例
 *
 * @see ResourceLoader
 * @see Resource
 * @see Value
 */
public class InjectionResourceLoaderDemo implements ResourceLoaderAware {

    private ResourceLoader awareResourceLoader;

    @Autowired
    private ResourceLoader autoWiredResourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        System.out.println("awareResourceLoader==autoWiredResourceLoader : " + (awareResourceLoader == autoWiredResourceLoader));
        System.out.println("applicationContext==autoWiredResourceLoader : " + (autoWiredResourceLoader == applicationContext));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectionResourceLoaderDemo.class);
        applicationContext.refresh();
        applicationContext.close();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.awareResourceLoader = resourceLoader;
    }
}
