package com.huajie.thinking.in.spring.dependency.injection.annotation;

import java.lang.annotation.*;


@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectUser {

}
