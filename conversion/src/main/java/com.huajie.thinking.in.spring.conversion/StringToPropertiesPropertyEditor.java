package com.huajie.thinking.in.spring.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * String 转换为 Properties {@link PropertyEditor} 实现
 *
 * @author ：xwf
 * @date ：Created in 2020-6-12 17:22
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport {

    // 1.实现 setAsText
    public void setAsText(String text) throws java.lang.IllegalArgumentException {
        // 2.将 String 转换为 Properties
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        // 3.临时存储 properties 对象
        setValue(properties);
    }

}
