package com.huajie.thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 XML 资源的依赖 Setter 注入 示例
 */
public class XmlDependencySetterInjectDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-setter-injection-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);
        System.out.println(bean.getUser());
    }

}
