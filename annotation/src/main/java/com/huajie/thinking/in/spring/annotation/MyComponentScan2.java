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
@MyComponentScan
public @interface MyComponentScan2 {

    @AliasFor(annotation = MyComponentScan.class, attribute = "scanBasePackages") // 隐性别名
            String[] basePackages() default {};


    /**
     * 与 MyComponentScan 中的属性同名
     *
     * @return
     */
    String[] scanBasePackages() default {};


    @AliasFor("scanBasePackages") // 显性覆盖
    String[] packages() default {};
}
