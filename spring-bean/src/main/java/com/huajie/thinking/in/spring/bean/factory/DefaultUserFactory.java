package com.huajie.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    //1.基于 @PostConstruct 注解
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct : UserFactory 初始化....");
    }

    public void initUserFactory() {
        System.out.println("自定义初始化 : UserFactory 初始化....");
    }

    public void destroyUserFactory() {
        System.out.println("自定义初始化 : UserFactory 销毁....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean : UserFactory 初始化....");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy : UserFactory 销毁....");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean  : UserFactory 销毁....");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("垃圾回收");
    }
}
