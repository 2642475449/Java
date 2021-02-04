package com.imooc.spring.aop;

import com.imooc.spring.aop.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.createUser();

    }
}
