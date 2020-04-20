package com.huajie.thinking.in.spring.ioc.overview.domain;

import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;

/**
 * 用户类
 */
public class User implements BeanNameAware {

    private String beanName;

    private Long id;

    private String name;

    private Integer age;

    private Resource configFileReource;

    private City city;

    private City[] cities;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City[] getCities() {
        return cities;
    }

    public void setCities(City[] cities) {
        this.cities = cities;
    }

    public Resource getConfigFileReource() {
        return configFileReource;
    }

    public void setConfigFileReource(Resource configFileReource) {
        this.configFileReource = configFileReource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static User createUser(String name) {
        User user = new User();
        user.setName(name);
        return user;
    }

    public static User createUser() {
        User user = new User();
        user.setName("static-user");
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", configFileReource=" + configFileReource +
                ", city=" + city +
                ", cities=" + Arrays.toString(cities) +
                '}';
    }

    @PostConstruct
    public void init() {
        System.out.println(beanName + "用户对象初始化...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(beanName + "用户对象销毁...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
