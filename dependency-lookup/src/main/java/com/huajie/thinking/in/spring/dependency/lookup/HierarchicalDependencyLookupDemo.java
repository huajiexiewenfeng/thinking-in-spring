package com.huajie.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性的依赖查找示例
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类{Configuration.class}
        applicationContext.register(HierarchicalDependencyLookupDemo.class);

        //1.获取 HierarchicalBeanFactory <- ConfigurableBeanFatory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前 BeanFactory 的 Parent BeanFactory" + beanFactory.getParentBeanFactory());
        beanFactory.setParentBeanFactory(createParentBeanFactory());
        System.out.println("当前 BeanFactory 的 Parent BeanFactory" + beanFactory.getParentBeanFactory());
        //当前没有
        displayLocalBean(beanFactory, "user");
        //父 BeanFactory 有
        displayLocalBean((HierarchicalBeanFactory) beanFactory.getParentBeanFactory(), "user");

        displayContainsBean(beanFactory, "user");
        //启动应用上下文
        applicationContext.refresh();

        applicationContext.close();
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory [%s] 是否包含 bean[name:%s] : %s\n", beanFactory, beanFactory,
                containsBean(beanFactory,beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory,beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory [%s] 是否包含 bean[name:%s] : %s\n", beanFactory, beanFactory,
                beanFactory.containsLocalBean(beanName));
    }

    private static BeanFactory createParentBeanFactory() {
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-injection-context.xml";
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }


}
