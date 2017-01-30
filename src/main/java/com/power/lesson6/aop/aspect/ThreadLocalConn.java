package com.power.lesson6.aop.aspect;

import java.sql.Connection;
import java.util.Stack;

/**
 * Created by shenli on 2017/1/27.
 */
public class ThreadLocalConn {

    private static final ThreadLocal<Stack<Connection>> localConn = new ThreadLocal<>();

    public static void pushConn(Connection connection) {
        Stack<Connection> connectionStack = localConn.get();
        if (connectionStack == null) {
            connectionStack = new Stack<Connection>();
            localConn.set(connectionStack);
        }
        connectionStack.push(connection);
    }

    public static Connection popConn() {
        Stack<Connection> connectionStack = localConn.get();
        if (connectionStack != null && !connectionStack.isEmpty()) {
            return connectionStack.pop();
        }
        return null;
    }

    public static Connection peekConn() {
        Stack<Connection> connectionStack = localConn.get();
        if (connectionStack != null && ! connectionStack.isEmpty()) {
            return connectionStack.peek();
        }
        return null;
    }

}
