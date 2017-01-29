package com.power.lesson6.dao.rowmapper;

import com.power.lesson6.bean.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shenli on 2017/1/26.
 */
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User u = new User();
        u.setId(rs.getLong("id"));
        u.setName(rs.getString("name"));
        u.setPass(rs.getString("pass"));
        u.setAge(rs.getInt("age"));
        return u;
    }
}
