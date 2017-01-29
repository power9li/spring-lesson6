package com.power.lesson6.dao.rowmapper;

import com.power.lesson6.bean.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shenli on 2017/1/28.
 */
public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setProductName(rs.getString("product_name"));
        order.setPrice(rs.getDouble("price"));
        return order;
    }
}
