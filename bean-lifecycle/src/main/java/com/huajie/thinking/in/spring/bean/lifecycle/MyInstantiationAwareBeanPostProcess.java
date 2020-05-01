package com.huajie.thinking.in.spring.bean.lifecycle;

import com.huajie.thinking.in.spring.bean.lifecycle.domain.UserHolder;
import com.huajie.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

public class MyInstantiationAwareBeanPostProcess implements InstantiationAwareBeanPostProcessor {
    /**
     * 实例化前阶段
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "superUser") && SuperUser.class.equals(beanClass)) {
            SuperUser user = new SuperUser();
            user.setName("new-superUser v1");
            return user;
        }
        return null;
    }

    /**
     * 实例化后阶段
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "user") && User.class.equals(bean.getClass())) {
            //"user" 对象不允许属性赋值（配置元信息-> 属性值）
            User user = User.class.cast(bean);
            user.setId(2L);
            user.setName("after-superUser v2");
            return false;//返回 false 表示忽略掉配置元信息，比如 <bean ...<property name = id value = 1/>
        }
        return true;
    }


    // user 跳过 Bean 属性赋值
    // superUser 也是完全跳过 Bean 实例化
    // 这里我们只能拦截 UserHolder
    /**
     * 属性赋值前阶段
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "userHolder") && UserHolder.class.equals(bean.getClass())) {
            final MutablePropertyValues propertyValues;
            // 兼容 pvs 为空的情况 pvs 对应xml中配置的 <property .../>
            if (pvs instanceof MutablePropertyValues) {
                propertyValues = (MutablePropertyValues) pvs;
            } else {
                propertyValues = new MutablePropertyValues();
            }
            // 此操作等价于 <property name="number" value="1"/>
            propertyValues.addPropertyValue("number", "1");
            if (propertyValues.contains("description")) {
                // PropertyValue 无法直接覆盖，因为是 final 类型
                PropertyValue description = propertyValues.getPropertyValue("description");
                propertyValues.removePropertyValue("description");
                propertyValues.addPropertyValue("description", "The user holder v2");
                System.out.println("属性赋值前阶段 : postProcessProperties() -> The user holder v2");

            }
            return propertyValues;
        }
        return null;
    }

    /**
     * 初始化前阶段
     * 这个接口实际上是覆盖的 BeanPostProcessor 中的方法
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "userHolder") && UserHolder.class.equals(bean.getClass())) {
            UserHolder user = UserHolder.class.cast(bean);
            user.setDescription("The user holder v3");
            System.out.println("初始化前阶段 : postProcessBeforeInitialization() -> The user holder v3");
        }
        return bean;
    }

    /**
     * 初始化后阶段
     * 这个接口实际上是覆盖的 BeanPostProcessor 中的方法
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "userHolder") && UserHolder.class.equals(bean.getClass())) {
            UserHolder user = UserHolder.class.cast(bean);
            user.setDescription("The user holder v7");
            System.out.println("初始化后阶段 : postProcessAfterInitialization() -> The user holder v7");
        }
        return bean;
    }

}