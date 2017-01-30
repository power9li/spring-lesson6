package com.power.spring.lesson6;

import com.power.lesson6.config.MySpringConfig;
import com.power.lesson6.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by shenli on 2017/1/30.
 */
public class TestJoinPoint {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MySpringConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.findProxy();
        System.out.println("userService = " + userService);
    }

}
