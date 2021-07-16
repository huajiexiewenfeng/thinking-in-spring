package com.huajie.thinking.in.spring.aop.features.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author: xiewenfeng
 * @Date: 2021/7/15 14:53
 */
@Service
public class UserImpl implements UserService {

  @Override
  @Cacheable(value = {"getUser"}, key = "#name")
  public User getUser(String name) {
    return User.createUser(name);
  }
}
