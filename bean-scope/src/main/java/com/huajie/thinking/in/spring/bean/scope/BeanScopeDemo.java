package com.huajie.thinking.in.spring.bean.scope;


import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;

/**
 * Bean 作用域
 */
public class BeanScopeDemo implements DisposableBean{

    @Bean
    //默认是singleton
    public static User singletonUser() {
        return createUser(System.nanoTime());
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static User prototypeUser() {
        return createUser(System.nanoTime());
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser2;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String, User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);

        //增加生命周期管理
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                    return bean;
                }

                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s Bean 名称：%s 在初始化后回调...%n", bean.getClass().getName(), beanName);
                    return bean;
                }
            });
        });


        applicationContext.refresh();
        //结论1：
        // Singleton Bean 无论依赖注入还是依赖查找，都是同一个对象
        // Prototype Bean 无论依赖注入还是依赖查找，都会生成一个新的对象

        //结论2：
        //如果依赖注入集合对象，Singleton Bean 和 Prototype Bean 都只会存在一个对象
        //Prototype Bean 有别于其他地方的依赖注入

        //结论3：
        //无论是 Singleton 还是 Prototype Bean 均会执行初始化方法回调
        //不过仅 Singleton Bean 会执行销毁方法回调

        scopeBeanByLookup(applicationContext);
        scopeBeanByInjection(applicationContext);

        applicationContext.close();
    }

    private static void scopeBeanByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser===" + singletonUser);

            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("prototypeUser===" + prototypeUser);
        }
    }

    private static void scopeBeanByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);
        System.out.println(demo.singletonUser1);
        System.out.println(demo.singletonUser2);
        System.out.println(demo.prototypeUser1);
        System.out.println(demo.prototypeUser2);

        System.out.println(demo.users);
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("当前 BeanScopeDemo Bean 正在销毁中...");
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();

        for (Map.Entry<String,User> entry:this.users.entrySet()){
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if(beanDefinition.isPrototype()){
                User user = entry.getValue();
                user.destroy();
            }
        }
    }

}
