package com.power.lesson6.service;

import com.power.lesson6.bean.User;
import com.power.lesson6.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
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
//        runtime();
        noRuntime();
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


}
