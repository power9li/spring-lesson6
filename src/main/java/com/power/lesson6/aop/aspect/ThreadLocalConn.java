package com.power.lesson6.aop.aspect;

import java.sql.Connection;

/**
 * Created by shenli on 2017/1/27.
 */
public class ThreadLocalConn {

    private static final ThreadLocal<Connection> localConn = new ThreadLocal<>();

    public static void setConn(Connection connection) {
        localConn.set(connection);
    }

    public static Connection getConn() {
        return localConn.get();
    }

}
