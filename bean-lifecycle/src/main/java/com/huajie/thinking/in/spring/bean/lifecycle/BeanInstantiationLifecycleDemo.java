package com.huajie.thinking.in.spring.bean.lifecycle;

import com.huajie.thinking.in.spring.bean.lifecycle.domain.UserHolder;
import com.huajie.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ObjectUtils;


/**
 * Bean 实例化生命周期
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
        System.out.println("------------------");
        executeApplicationContext();
    }

    private static void executeApplicationContext(){
        String[] locations = {"classpath:/META-INF/dependency-lookup-context.xml", "classpath:/META-INF/bean-constructor-injection.xml"};

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(locations);

        //第一种添加 BeanpostBeanProcess 实现方式：
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
//        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcess());
        //第二种添加 BeanpostBeanProcess 实现方式：
        //将 <bean class="com.huajie.thinking.in.spring.bean.lifecycle.MyInstantiationAwareBeanPostProcess"/> 配置在 xml 中

        applicationContext.refresh();

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);

        System.out.println(userHolder);

        applicationContext.close();
    }


    private static void executeBeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加 BeanpostBeanProcess 实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcess());
        //内部类 无法添加 ApplicationContextAwareProcessor
//        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(beanFactory));

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"classpath:/META-INF/dependency-lookup-context.xml", "classpath:/META-INF/bean-constructor-injection.xml"};
        int count = reader.loadBeanDefinitions(locations);
//        System.out.println("Bean 定义的数量: " + count);
//        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
//        Stream.of(beanDefinitionNames).forEach(System.out::println);

        User user = beanFactory.getBean("user", User.class);
        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        // 构造器注入是按照类型注入，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);

        System.out.println(userHolder);
    }

}
