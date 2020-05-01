package com.huajie.thinking.in.spring.bean.lifecycle;

import com.huajie.thinking.in.spring.bean.lifecycle.domain.UserHolder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals(beanName, "userHolder") && UserHolder.class.equals(bean.getClass())) {
            UserHolder user = UserHolder.class.cast(bean);
            user.setDescription("The user holder v9");
            System.out.println("销毁前阶段 : postProcessBeforeDestruction() -> The user holder v9");
        }
    }
    
}
