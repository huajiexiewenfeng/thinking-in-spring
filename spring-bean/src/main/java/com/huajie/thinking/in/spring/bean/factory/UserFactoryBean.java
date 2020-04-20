package com.huajie.thinking.in.spring.bean.factory;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser("FactoryBean-user");
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
