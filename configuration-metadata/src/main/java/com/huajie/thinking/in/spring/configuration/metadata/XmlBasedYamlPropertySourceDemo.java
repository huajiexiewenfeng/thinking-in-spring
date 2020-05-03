package com.huajie.thinking.in.spring.configuration.metadata;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;


/**
 * 基于 xml 的 yaml 外部化配置示例
 */
public class XmlBasedYamlPropertySourceDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/yaml-property-source-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(location);

        Map<String, Object> users = beanFactory.getBean("yamlMap", Map.class);

        System.out.println(users);
    }
}
