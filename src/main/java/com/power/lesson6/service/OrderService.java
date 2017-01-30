package com.power.lesson6.service;

import com.power.lesson6.bean.Order;
import com.power.lesson6.dao.OrderDao;
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
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public List<Order> findAll(){
        return orderDao.findAll();
    }

    public boolean updateOrder(Order order) throws FileNotFoundException {
        int num = orderDao.updateOrder(order);
//        runtime();
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

}
