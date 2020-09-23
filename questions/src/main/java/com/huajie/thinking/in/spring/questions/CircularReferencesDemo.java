package com.huajie.thinking.in.spring.questions;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 循环依赖示例
 *
 * @author ：xwf
 * @date ：Created in 2020-8-7 15:39
 */
public class CircularReferencesDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册
        applicationContext.register(CircularReferencesDemo.class);
        // 循环引用默认开启
        applicationContext.setAllowCircularReferences(true);
        // 开启
        applicationContext.refresh();
        Student student = applicationContext.getBean(Student.class);
        ClassRoom classRoom = applicationContext.getBean(ClassRoom.class);
        System.out.println("student:" + student);
        System.out.println("classRoom:" + classRoom);
        // 关闭
        applicationContext.close();
    }

    @Bean
    public Student student() {
        Student student = new Student();
        student.setId(1L);
        student.setName("小仙");
        return student;
    }

    @Bean
    public ClassRoom classRoom() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setId(1L);
        classRoom.setName("教室1");
        return classRoom;
    }

}
