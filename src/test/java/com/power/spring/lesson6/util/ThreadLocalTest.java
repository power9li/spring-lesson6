package com.power.spring.lesson6.util;

import com.power.lesson6.aop.aspect.ThreadLocalConn;
import com.power.lesson6.config.MySpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by shenli on 2017/1/27.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MySpringConfig.class)
public class ThreadLocalTest {

    @Autowired
    private DataSource ds;

    @Test
    public void test() throws Exception {

        Thread t1 = new Thread("Thread1"){
            Connection connection = null;

            @Override
            public void run() {
                try {
                    for(int i=0; i< 10; i++) {
                        connection = ThreadLocalConn.popConn();
                        if (connection == null) {
                            connection = ds.getConnection();
                            ThreadLocalConn.pushConn(connection);
                        }
                        System.out.println("Thread1.connection = " + connection);
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };


        Thread t2 = new Thread("Thread2"){
            Connection connection = null;
            @Override
            public void run() {
                try {
                    for(int i=0; i< 10; i++) {
                        connection = ThreadLocalConn.popConn();
                        if (connection == null) {
                            connection = ds.getConnection();
                            ThreadLocalConn.pushConn(connection);
                        }
                        System.out.println("Thread2.connection = " + connection);
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }


}
