package com.huajie.thinking.in.spring.ioc.overview.dependency.injection;

import com.huajie.thinking.in.spring.ioc.overview.annotation.Super;
import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import com.huajie.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * 依赖注入示例
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        //配置 xml 配置文件
        //启动 spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml ");
        //自定义Bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
//        System.out.println(userRepository.getUser());
//        System.out.println(userRepository.getUsers());

        //内建依赖
//        BeanFactory beanFactory1 = userRepository.getBeanFactory();
//        BeanFactory beanFactory2 = beanFactory.getBean(BeanFactory.class);
//        System.out.println("beanFatory1---"+beanFactory1);
//        System.out.println("beanFatory2---"+beanFactory2);
//        System.out.println("注入的 BeanFatory 是否和当前的 BeanFatory 相等："+ (beanFactory1==beanFactory));


//        ObjectFactory<User> objectFactory = userRepository.getUserObjectFactory();
//        System.out.println(objectFactory.getObject());

        //容器内建 Bean
        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject() == beanFactory);

        //容器内建 Bean
        Environment bean = beanFactory.getBean(Environment.class);
        System.out.println(bean);

        whoIsIocContainer(userRepository, beanFactory);
    }

    private static void whoIsIocContainer(UserRepository userRepository, BeanFactory beanFactory) {

        // ConfigurableApplicationContext -> ApplicationContext -> BeanFactory
        // 在上下文的实现中采用的是组合模式，org.springframework.context.support.AbstractRefreshableApplicationContext 中
        // 在接口的实现上又继承了 BeanFactory
        System.out.println("userRepository.getBeanFactory():" + userRepository.getBeanFactory());
        System.out.println("beanFactory:" + beanFactory);
        System.out.println("两者是否相等:" + (userRepository.getBeanFactory() == beanFactory));

    }

}
