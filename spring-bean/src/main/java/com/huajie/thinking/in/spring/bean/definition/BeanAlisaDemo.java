package com.huajie.thinking.in.spring.bean.definition;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名示例
 */
public class BeanAlisaDemo {
    public static void main(String[] args) {
        //配置 xml 配置文件
        //启动 spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");
        User xwfUser = (User) beanFactory.getBean("xwf-user");
        User user = (User) beanFactory.getBean("user");
        System.out.println(xwfUser==user);
    }
}
