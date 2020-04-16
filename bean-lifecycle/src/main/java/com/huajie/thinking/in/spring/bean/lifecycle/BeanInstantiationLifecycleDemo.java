package com.huajie.thinking.in.spring.bean.lifecycle;

import com.huajie.thinking.in.spring.ioc.overview.domain.SuperUser;
import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.beans.PropertyDescriptor;
import java.util.stream.Stream;

public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加 BeanpostBeanProcess 实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcess());
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

    static class MyInstantiationAwareBeanPostProcess implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//            if(ObjectUtils.nullSafeEquals(beanName,"superUser")){
//                SuperUser user = new SuperUser();
//                user.setName("new-superUser v1");
//                return user;
//            }
            return null;
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals(beanName, "user")) {
                //"user" 对象不允许属性赋值（配置元信息-> 属性值）
                User user = User.class.cast(bean);
                user.setName("after-superUser v2");
                return false;
            }
            return true;
        }

        // user 跳过 Bean 属性赋值
        // superUser 也是完全跳过 Bean 实例化

        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals(beanName, "superUser")) {
                final MutablePropertyValues propertyValues;
                if (pvs instanceof MutablePropertyValues) {
                    propertyValues = (MutablePropertyValues) pvs;
                } else {
                    propertyValues = new MutablePropertyValues();
                }
                propertyValues.addPropertyValue("address", "shanghai");
                propertyValues.addPropertyValue("name", "lixiaoyun v3");
                return propertyValues;
            }

            return null;
        }

    }
}
