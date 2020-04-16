package com.huajie.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link BeanCreationException}示例
 */
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //在初始化的时候，抛出异常
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);

        applicationContext.registerBeanDefinition("exception-bean", beanDefinitionBuilder.getBeanDefinition());

        //启动应用上下文
        applicationContext.refresh();

        applicationContext.close();
    }

    static class POJO implements InitializingBean{

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("For purposes...");
        }
    }

}
