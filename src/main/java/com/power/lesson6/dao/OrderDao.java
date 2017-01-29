package com.power.lesson6.dao;

import com.power.lesson6.bean.Order;
import com.power.lesson6.dao.rowmapper.OrderRowMapper;
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
 * Created by shenli on 2017/1/28.
 */
@Component
public class OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int updateOrder(final Order order) {
//        System.out.println("jdbcTemplate = " + jdbcTemplate);
        Integer execute = jdbcTemplate.execute(new ConnectionCallback<Integer>() {
            @Override
            public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
//                System.out.println("UserDao.doInConnection,con="+con);
                PreparedStatement ps = con.prepareStatement("update orders set product_name = ?,price = ? where id = ?");
                ps.setString(1, order.getProductName());
                ps.setDouble(2, order.getPrice());
                ps.setLong(3, order.getId());
                return ps.executeUpdate();
            }
        });
        return execute;
    }

    public List<Order> findAll() {
        List<Order> orders = jdbcTemplate.query("select * from orders", new OrderRowMapper());
        return orders;
    }

}
