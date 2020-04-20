package com.huajie.thinking.in.spring.bean.definition;

import com.huajie.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.huajie.thinking.in.spring.bean.factory.UserFactory;
import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Bean 特殊实例化示例
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        //通过 ApplicationContext 获取 AutowireCapableBeanFactory
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

//        ServiceLoader<UserFactory> load = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
//        demoServiceLoader();
//        displayServiceLoader(load);
        UserFactory bean = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(bean.createUser());
    }

    /**
     * jdk中的反转控制
     */
    public static void demoServiceLoader() {
        ServiceLoader<UserFactory> load = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(load);
    }

    public static void displayServiceLoader(ServiceLoader<UserFactory> load) {
        Iterator<UserFactory> iterator = load.iterator();
        while (iterator.hasNext()) {
            UserFactory next = iterator.next();
            System.out.println(next.createUser());
        }
    }

}
