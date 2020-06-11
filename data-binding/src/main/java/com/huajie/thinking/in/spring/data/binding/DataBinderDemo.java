package com.huajie.thinking.in.spring.data.binding;

import com.huajie.thinking.in.spring.ioc.overview.domain.Company;
import com.huajie.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

/**
 * {@link DataBinder} 示例
 *
 * @see DataBinder
 */
public class DataBinderDemo {
    public static void main(String[] args) {
        User user = new User();
        DataBinder dataBinder = new DataBinder(user, "user");

        MutablePropertyValues mpvs = new MutablePropertyValues();

        mpvs.add("id", "1")
                .add("name", "xwf")
                // 添加一个不存在的属性值
                // DataBinder 忽略未知属性
                .add("otherName", "xwf")
                // 嵌套属性
                .add("company.name", "阿里");

        // 1.ignoreUnknownFields true(默认) -> false
//        dataBinder.setIgnoreUnknownFields(false);

        // 2.autoGrowNestedPaths true(默认) -> false
        dataBinder.setAutoGrowNestedPaths(false);

        // 3.ignoreInvalidFields false(默认) -> true
        dataBinder.setIgnoreInvalidFields(true);

        // 4.requiredFields 设置不能为空的字段
        dataBinder.setRequiredFields("id","name","age");

//        dataBinder.setAllowedFields("id");

        dataBinder.setDisallowedFields("id");

        dataBinder.bind(mpvs);
        // 输出
        System.out.println(user);
        // 绑定结果(结果包含了错误文案信息，但是不会报错)
        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult);

    }
}
