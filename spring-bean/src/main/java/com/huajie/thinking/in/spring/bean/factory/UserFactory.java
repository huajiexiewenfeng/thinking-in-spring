package com.huajie.thinking.in.spring.bean.factory;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link User} 工厂类
 */
public interface UserFactory {
    default User createUser(){
        return User.createUser("工厂Bean-user");
    }
}
