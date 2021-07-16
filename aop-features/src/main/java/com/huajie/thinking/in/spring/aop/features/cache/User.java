package com.huajie.thinking.in.spring.aop.features.cache;

/**
 * @Author: xiewenfeng
 * @Date: 2021/7/15 14:54
 */
public class User {

  private String name;
  private Integer age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public static User createUser(String name) {
    User user = new User();
    user.setAge(18);
    user.setName(name);
    return user;
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", age=" + age +
        '}';
  }
}
