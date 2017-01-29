package com.power.lesson6.dao;

import com.power.lesson6.bean.User;
import com.power.lesson6.dao.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by shenli on 2017/1/26.
 */
@Component
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int updateUser(final User user) {
//        System.out.println("jdbcTemplate = " + jdbcTemplate);
        Integer execute = jdbcTemplate.execute(new ConnectionCallback<Integer>() {
            @Override
            public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
//                System.out.println("UserDao.doInConnection,con="+con);
                PreparedStatement ps = con.prepareStatement("update users set name = ?,pass = ?,age = ? where id = ?");
                ps.setString(1, user.getName());
                ps.setString(2, user.getPass());
                ps.setInt(3, user.getAge());
                ps.setLong(4, user.getId());
                return ps.executeUpdate();
            }
        });
        return execute;
    }

    public List<User> findAll() {
        List<User> users = jdbcTemplate.query("select * from users", new UserRowMapper());
        return users;
    }
}
