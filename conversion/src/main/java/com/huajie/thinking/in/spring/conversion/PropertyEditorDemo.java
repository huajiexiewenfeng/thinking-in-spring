package com.huajie.thinking.in.spring.conversion;

import java.beans.PropertyEditor;

/**
 * {@link PropertyEditor} 示例
 *
 * @author ：xwf
 * @date ：Created in 2020-6-12 17:20
 * @see PropertyEditor
 */
public class PropertyEditorDemo {
    public static void main(String[] args) {
        // 模拟 spring framework 操作
        String text = "name = 小仙";
        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        propertyEditor.setAsText(text);
        propertyEditor.getAsText();
        System.out.printf("类型：%s，值：%s\n", propertyEditor.getValue().getClass(), propertyEditor.getValue());
        System.out.printf("getAsText 值：%s\n", propertyEditor.getAsText());

    }
}
