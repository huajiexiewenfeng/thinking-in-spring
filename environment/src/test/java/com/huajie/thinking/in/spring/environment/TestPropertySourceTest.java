package com.huajie.thinking.in.spring.environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link TestPropertySource} 示例
 *
 * @author ：xwf
 * @date ：Created in 2020-10-9 22:56
 */
@TestPropertySource(properties = "user.name=小仙")
@ContextConfiguration(classes = TestPropertySourceTest.class) // Spring 注解驱动测试注解
@RunWith(SpringRunner.class)
public class TestPropertySourceTest {

    @Value("${user.name}")
    private String userName;

    @Autowired
    private Environment environment;

    @Test
    public void test(){
        System.out.println(userName);
        MutablePropertySources propertySources = ((ConfigurableEnvironment)environment).getPropertySources();
        int i = 0;
        for (org.springframework.core.env.PropertySource<?> propertySource : propertySources) {
            i++;
            System.out.printf("顺序[%d]-名称[%s]:[%s]\n", i, propertySource.getName(), propertySource.toString());
        }
    }
}
