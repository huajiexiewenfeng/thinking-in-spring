package com.huajie.thinking.in.spring.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 自定义 {@link org.springframework.stereotype.Component} Scan
 *
 * @author ：xwf
 * @date ：Created in 2020-9-23 23:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@ComponentScan
public @interface MyComponentScan {

    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages") // 隐性别名
    String[] scanBasePackages() default {};

}
