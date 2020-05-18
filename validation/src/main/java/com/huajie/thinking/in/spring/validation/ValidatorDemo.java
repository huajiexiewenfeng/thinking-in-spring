package com.huajie.thinking.in.spring.validation;

import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.validation.*;

import java.util.Locale;

import static com.huajie.thinking.in.spring.validation.ErrorsMessageDemo.createMessageSource;

/**
 * 自定义 Spring {@link Validator} 实现示例
 *
 * @see Validator
 */
public class ValidatorDemo {
    public static void main(String[] args) {
        // 1.创建 Validator
        Validator validator = new UserValidator();
        // 2.判断是否支持目标对象的类型
        User user = new User();
        System.out.println("user 对象是否被 UserValidator 支持："+validator.supports(user.getClass()));
        // 3.创建 Errors 对象
        Errors errors =  new BeanPropertyBindingResult(user,"user");
        // 4.创建 MessageSource
        MessageSource messageSource = createMessageSource();
        validator.validate(user,errors);
        for (ObjectError error : errors.getAllErrors()) {
            String message = messageSource.getMessage(error.getCode(),error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
//            User user = (User) target;
//            if(StringUtils.isEmpty(user.getName())){
//                errors.rejectValue("name","name.required");
//            }
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","name.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"id","id.required");
        }
    }
}
