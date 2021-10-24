package com.geek.homework.week05.bean;

import com.geek.homework.week05.bean.config.SpringConfiguration;
import com.geek.homework.week05.bean.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeans {

    @Test
    public void testBeanByXml(){
        //D:\study\01_geekbang\GeekHomeWork\homework\src\main\resources\week05\beans.xml
        String xmlPath = "com/geek/homework/week05/bean/beans.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        System.out.println(applicationContext.getBean("user"));
    }

    @Test
    public void testBeanAnno(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService userService = (UserService)applicationContext.getBean("userServiceImpl");
        System.out.println("登录状态:" + userService.login("admin","admin"));
    }
}
