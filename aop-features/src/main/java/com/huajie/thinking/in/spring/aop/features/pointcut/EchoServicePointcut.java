package com.huajie.thinking.in.spring.aop.features.pointcut;

import java.lang.reflect.Method;
import java.util.Objects;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

/**
 * @Author: xiewenfeng
 * @Date: 2021/7/5 14:26
 */
public class EchoServicePointcut extends StaticMethodMatcherPointcut {

  private String methodName;

  private Class targetClass;

  public EchoServicePointcut(String methodName, Class targetClass) {
    this.methodName = methodName;
    this.targetClass = targetClass;
  }

  @Override
  public boolean matches(Method method, Class<?> targetClass) {
    // 方法名称相同，并且是 targetClass 的子类
    return Objects.equals(methodName, method.getName()) && this.targetClass
        .isAssignableFrom(targetClass);
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public Class getTargetClass() {
    return targetClass;
  }

  public void setTargetClass(Class targetClass) {
    this.targetClass = targetClass;
  }
}
