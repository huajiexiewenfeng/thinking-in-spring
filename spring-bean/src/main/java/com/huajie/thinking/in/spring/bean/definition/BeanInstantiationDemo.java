package com.huajie.thinking.in.spring.bean.definition;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化示例
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");

        User xwfUser = (User) beanFactory.getBean("user-static-method");

        User instanceUser = (User) beanFactory.getBean("user-instance-method");

        User factoryUser = (User) beanFactory.getBean("user-by-factory-bean");

        System.out.println(xwfUser);
        System.out.println(instanceUser);
        System.out.println(factoryUser);
    }
}
