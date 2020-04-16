package com.huajie.thinking.in.spring.bean.definition;

import com.huajie.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.huajie.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体 Bean 注册实例
 */
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册外部单例对象
        UserFactory userFactory = new DefaultUserFactory();
        //
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        //注册外部单例对象
        beanFactory.registerSingleton("singleton-user-factory",userFactory);
        //启动应用上下文
        applicationContext.refresh();
        //通过依赖查找的方式获取 UserFactory
        UserFactory bean = beanFactory.getBean("singleton-user-factory", UserFactory.class);
        System.out.println(bean.createUser());
        System.out.println(userFactory==bean);

        //关闭
        applicationContext.close();


    }


}
