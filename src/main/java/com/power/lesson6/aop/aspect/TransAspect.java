package com.power.lesson6.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by shenli on 2017/1/27.
 */
@Component
@Aspect
public class TransAspect {

    @Autowired
    private DataSource dataSource;

//    @Before("execution(* com.power.lesson6.service.*Service.*(..))")
    public void traceBusiness(JoinPoint joinPoint) {
        System.out.println("joinPoint: [\n\ttype = " + joinPoint.getKind()
                + ", \n\tdeclaringTypeName = " + joinPoint.getSignature().getDeclaringTypeName()
                + ", \n\tthis = " + joinPoint.getThis()
                + ", \n\ttarget = " + joinPoint.getTarget()
                + ", \n\tmethod = " + joinPoint.getSignature().getName()
                + ", \n\targs = " + Arrays.toString(joinPoint.getArgs()) + "\n]");
    }

    @Around("execution(* com.power.lesson6.service.*Service.save*(..)) || " +
            "execution(* com.power.lesson6.service.*Service.update*(..)) ||" +
            "execution(* com.power.lesson6.service.*Service.delete*(..)) ||" +
            "execution(* com.power.lesson6.service.*Service.create*(..))")
    public Object transWriteAspect(ProceedingJoinPoint pjp){

        Object rst = null;
        Connection conn = null;
        try {
            conn = getConn();
            System.out.println("[" + Thread.currentThread().getName()
                    + "] method="+pjp.getSignature().getName()+",conn=" + conn);
            System.out.println("conn.setAutoCommit(false);");
            conn.setAutoCommit(false);
            rst = pjp.proceed();
            conn.commit();
            System.out.println("executed success, commit.");

        } catch (Throwable throwable) {
            if (throwable instanceof RuntimeException) {
                doRollBack(conn,throwable);
            }
            else{
                doCommit(conn,throwable);
            }
        } finally {
            doClose(conn);
        }
        return rst;
    }


    @Around("execution(* com.power.lesson6.service.*Service.find*(..)) || " +
            "execution(* com.power.lesson6.service.*Service.query*(..))")
    public Object transReadAspect(ProceedingJoinPoint pjp){
        Object rst = null;
        Connection conn = null;
        try {
            conn = getConn();
            System.out.println("[" + Thread.currentThread().getName() +
                    "] method="+pjp.getSignature().getName()
                    +",conn=" + conn);
            System.out.println("conn.setReadOnly(true);");
            conn.setReadOnly(true);
            System.out.println("conn.setAutoCommit(true);");
            conn.setAutoCommit(true);
            rst = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            doClose(conn);
        }
        return rst;
    }

    private Connection getConn(){
        Connection conn = ThreadLocalConn.peekConn();
        try {
            if(conn == null || conn.isClosed() ) {
                try {
                    conn = dataSource.getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ThreadLocalConn.pushConn(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    private void doRollBack(Connection conn,Throwable throwable) {
        try {
            System.out.println("is RuntimeException : "+throwable.getMessage()+" ,rollback.");
            conn.rollback();
        } catch (SQLException e) {
        }
    }

    private void doCommit(Connection conn, Throwable throwable) {
        try {
            System.out.println("not RuntimeException :"+throwable.getMessage()+" ,commit.");
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void doClose(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }
}
