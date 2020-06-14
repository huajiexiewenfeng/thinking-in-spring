package com.huajie.thinking.in.spring.conversion;


import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * String 转换为 Properties {@link PropertyEditor} 实现
 *
 * @author ：xwf
 * @date ：Created in 2020-6-12 17:22
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport {

    private static final String ENCODING = "utf-8";

    // 1.实现 setAsText
    public void setAsText(String text) throws java.lang.IllegalArgumentException {
        // 2.将 String 转换为 Properties
        Properties properties = new Properties();
        try {
            Reader reader = new InputStreamReader(new ByteArrayInputStream(text.getBytes(ENCODING)));
            properties.load(reader);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        // 3.临时存储 properties 对象
        setValue(properties);
    }

    public String getAsText() {
        Properties properties = (Properties) getValue();

        StringBuilder textBuilder = new StringBuilder();
        for (Map.Entry entry :
                properties.entrySet()) {
            textBuilder.append(entry.getKey()).append("=")
                    .append(entry.getValue())
                    .append(System.getProperty("line.separator"));
        }
        return textBuilder.toString();
    }

}
