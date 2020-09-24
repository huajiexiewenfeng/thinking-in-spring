package com.huajie.thinking.in.spring.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

/**
 * 偶数 Profile 条件
 * @author ：xwf
 * @date ：Created in 2020-9-24 22:58
 */
public class EvenProfileCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        for (String profile:  activeProfiles) {
            if(Objects.equals(profile,"even")){
                return true;
            }
        }
        return false;
    }

}
