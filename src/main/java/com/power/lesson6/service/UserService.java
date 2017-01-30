package com.power.lesson6.service;

import com.power.lesson6.bean.User;
import com.power.lesson6.dao.UserDao;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by shenli on 2017/1/26.
 */
@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findAll(){
        return userDao.findAll();
    }

    public boolean updateUser(User user) throws FileNotFoundException {
        int num = userDao.updateUser(user);
        runtime();
//        noRuntime();
        return num > 0;
    }

    public void runtime(){
        String[] a = new String[]{};
        System.out.println("a[1] = " + a[1]);
    }

    public void noRuntime() throws FileNotFoundException {
        File f = new File("/ABC/");
        new FileInputStream(f);
        System.out.println(f.getTotalSpace());
    }

    public void findProxy(){
        System.out.println("UserService.printProxy");
        //AopContext.currentProxy()要生效,一定要设置exposeProxy=true
        Object o = AopContext.currentProxy();
        System.out.println("proxy = " + o);
        ((UserService)o).findAll();
    }

    public void find2(){
        User jack = userDao.findByName("Jack");
        System.out.println("jack = " + jack);
        UserService us = (UserService)AopContext.currentProxy();
        try {
            us.updateUser(jack);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
