package com.huajie.thinking.in.spring.bean.lifecycle;

import com.huajie.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.stream.Stream;


/**
 * BeanDefinition 合并示例
 */
public class MergedBeanDefinitionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        int count = reader.loadBeanDefinitions(location);
        System.out.println(count);
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);

        User user = beanFactory.getBean("user", User.class);
        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);

        System.out.println(user);
        System.out.println(superUser);
    }
}
