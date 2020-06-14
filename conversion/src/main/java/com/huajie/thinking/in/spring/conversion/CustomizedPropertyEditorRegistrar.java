package com.huajie.thinking.in.spring.conversion;

import com.huajie.thinking.in.spring.conversion.domain.Company;
import com.huajie.thinking.in.spring.conversion.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * 自定义{@link PropertyEditorRegistrar} 实现
 *
 * @Author xwf
 * @Date 2020\6\13 0013 7:12
 * @see PropertyEditorRegistrar
 */
//@Component // 3.将其声明为 Spring Bean 本例采用xml方式 注解同样可以
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        // 1.通用类型转换
        // 2.Java Bean 属性类型转换
        registry.registerCustomEditor(Company.class, new CompanyTypeEditor());
    }
}
