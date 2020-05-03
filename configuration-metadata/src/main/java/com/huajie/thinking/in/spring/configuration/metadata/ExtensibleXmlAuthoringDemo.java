package com.huajie.thinking.in.spring.configuration.metadata;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Spring xml 元素扩展示例
 */
public class ExtensibleXmlAuthoringDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/users-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(location);

        User bean = beanFactory.getBean(User.class);

        System.out.println(bean);
    }
}
