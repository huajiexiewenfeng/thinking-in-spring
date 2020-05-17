package com.huajie.thinking.in.spring.i18n;


import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * {@link MessageFormat} 示例
 *
 * @see MessageFormat
 */
public class MessageFormatDemo {
    public static void main(String[] args) {
        javaDocDemo();

        // 重置 MessageFormatPatten
        MessageFormat messageFormat = new MessageFormat("This is a text : {0}");
        messageFormat.applyPattern("This is a new text : {0}");
        String result = messageFormat.format(new Object[]{"hello,world"});
        System.out.println(result);

        // 重置 Locale
        messageFormat.setLocale(Locale.ENGLISH);
        messageFormat.applyPattern("At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.");
        int planet = 7;
        String event = "a disturbance in the Force";
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);

        // 重置 Format
        // 根据参数索引来设置 Pattern
        messageFormat.setFormat(1,new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"));
        result = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result);
    }

    private static void javaDocDemo() {
        int planet = 7;
        String event = "a disturbance in the Force";

        String result = MessageFormat.format(
                "At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.",
                planet, new Date(), event);

        System.out.println(result);
    }

}
