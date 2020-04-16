package com.huajie.thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * "byName" Autowiring 依赖 setter 注入示例
 */
public class AutoWiringByNameDependencySetterInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/autowiring-dependency-setter-injection-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);

        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);
        System.out.println(bean.getUser());
    }
}
