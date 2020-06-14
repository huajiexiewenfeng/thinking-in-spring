package com.huajie.thinking.in.spring.conversion;

import com.huajie.thinking.in.spring.conversion.domain.Company;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

/**
 * {@link Company} 类型 {@link PropertyEditor} 自定义实现
 *
 * @author ：xwf
 * @date ：Created in 2020\6\13 0013 19:04
 * @see Company
 */
public class CompanyTypeEditor extends PropertyEditorSupport {
    public void setAsText(String text) {
        String[] split = text.split(",");
        Company company = new Company();
        if (split.length > 1) {
            company.setName(split[0]);
            company.setAddress(split[1]);
        } else {
            company.setName(text);
        }
        setValue(company);
    }
}
