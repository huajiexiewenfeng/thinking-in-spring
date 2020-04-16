package com.huajie.thinking.in.spring.bean.definition;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建示例
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        //1.通过 BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //通过属性设置
        beanDefinitionBuilder.addPropertyValue("age", 18)
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "xwf");
        // 获取 BeanDefinition 对象
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // beanDefinition 并非 Bean 的终态 可以自定义修改

        //2.通过 AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        //通过属性设置
        MutablePropertyValues propertyValues = new MutablePropertyValues();
//        propertyValues.addPropertyValue("age", 18);
//        propertyValues.addPropertyValue("id", 1L);
//        propertyValues.addPropertyValue("name", "xwf");dependency-lookup-context.xml
        propertyValues.add("age", 18)
                .add("id", 1L)
                .add("name", "xwf");
        genericBeanDefinition.setPropertyValues(propertyValues);
    }
}
