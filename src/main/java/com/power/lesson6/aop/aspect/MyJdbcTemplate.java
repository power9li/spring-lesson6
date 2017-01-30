package com.power.lesson6.aop.aspect;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by shenli on 2017/1/27.
 */
public class MyJdbcTemplate extends JdbcTemplate {

    public MyJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public <T> T execute(ConnectionCallback<T> action)
            throws DataAccessException {
        System.out.println("MyJdbcTemplate.execute(ConnectionCallback action)");
        Assert.notNull(action, "Callback object must not be null");
        T rst = null;
        try {
            Connection conToUse = ThreadLocalConn.popConn();
            System.out.println(Thread.currentThread().getName()+
                    " ThreadLocalConn.popConn()="+conToUse);
            rst = action.doInConnection(conToUse);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rst;
    }

    @Override
    public <T> T execute(StatementCallback<T> action) throws DataAccessException {
        Assert.notNull(action, "Callback object must not be null");
        System.out.println("MyJdbcTemplate.execute(StatementCallback action)");
        T result = null;
        Statement stmt = null;
        try {
            Connection conToUse = ThreadLocalConn.popConn();
            System.out.println(Thread.currentThread().getName()+
                    " ThreadLocalConn.popConn()="+conToUse);
            stmt = conToUse.createStatement();
            applyStatementSettings(stmt);
            Statement stmtToUse = stmt;

            result = action.doInStatement(stmtToUse);
            handleWarnings(stmt);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
