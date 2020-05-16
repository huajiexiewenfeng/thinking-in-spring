package com.huajie.thinking.in.spring.resource;

import com.huajie.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * 注入 {@link Resource} 示例
 *
 * @see Resource
 * @see Value
 */
public class InjectionResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource resource;

    @Value("classpath:/META-INF/*.properties")
    private Collection<Resource> resources;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resourcesArray;

    @Value("${user.dir}")
    private String currentPath;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectionResourceDemo.class);
        applicationContext.refresh();
        InjectionResourceDemo bean = applicationContext.getBean(InjectionResourceDemo.class);

        String content = ResourceUtils.getContent(bean.resource);
        System.out.println(content);
        System.out.println("================");
        System.out.println(bean.currentPath);
        System.out.println("================");
        System.out.println(bean.resources);
        bean.resources.forEach(resource -> System.out.println(ResourceUtils.getContent(resource)));
        System.out.println("================");
        Stream.of(bean.resourcesArray).forEach(resource -> System.out.println(ResourceUtils.getContent(resource)));

        applicationContext.close();
    }
}
