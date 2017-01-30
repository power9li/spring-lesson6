package com.power.lesson6.dao;

import com.power.lesson6.bean.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by shenli on 2017/1/30.
 */
public class BaseDao<T extends Domain> {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int createDomain(T domain) {
        Integer execute = jdbcTemplate.execute(new ConnectionCallback<Integer>() {
            @Override
            public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
                PreparedStatement ps = con.prepareStatement("INSERT INTO "+ domain.getTableName());
//                ps.setString(1, user.getName());
//                ps.setString(2, user.getPass());
//                ps.setInt(3, user.getAge());
//                ps.setLong(4, user.getId());
                return ps.executeUpdate();
            }
        });
        return execute;
    }

    public Domain findById(Serializable id) {
        return null;
    }

    public void updateDomain(T domain) {

    }

    public void deleteDomain(Serializable id) {

    }

    public List<T> findAll() {
        return null;
    }
}
