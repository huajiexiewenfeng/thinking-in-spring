package com.huajie.thinking.in.spring.conversion;

import com.huajie.thinking.in.spring.conversion.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditor;

/**
 * 自定义{@link PropertyEditor} 示例
 *
 * @Author xwf
 * @Date 2020\6\13 0013 7:37
 */
public class SpringCustomizedPropertyEditorDemo {
    public static void main(String[] args) {
        // 创建并启动 BeanFactory 容器
        ConfigurableApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:/META-INF/property-editors-context.xml");

        User user = applicationContext.getBean("user", User.class);

        System.out.println(user);

        applicationContext.close();
    }
}
