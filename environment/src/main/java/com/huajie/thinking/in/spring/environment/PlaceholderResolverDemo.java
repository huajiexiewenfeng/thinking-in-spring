package com.huajie.thinking.in.spring.environment;


import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * {@link PropertyPlaceholderConfigurer} 示例
 * @author ：xwf
 * @date ：Created in 2020\7\19 0019 18:26
 */
public class PlaceholderResolverDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/placeholders-resolver-context.xml");
        User user = context.getBean(User.class);
        System.out.println(user);
    }
}
