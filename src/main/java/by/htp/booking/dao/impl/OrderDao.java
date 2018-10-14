package by.htp.booking.dao.impl;

import by.htp.booking.bean.Order;
import by.htp.booking.dao.BeanDao;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDao extends BeanDao<Order> {

    @Override
    public List<Order> getAll(String where) {
        List<Order> orderList = new ArrayList<>();
        String sql = String.format("Select * from `order`" + where);
        try (Connection connection = DaoConnect.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setBeginDate(rs.getLong("beginDate"));
                order.setCountDay(rs.getInt("countDay"));
                order.setEndDate(rs.getLong("endDate"));
                order.setApartmentId(rs.getInt("apartmentId"));
                order.setPrice(rs.getDouble("price"));
                order.setTotal(rs.getDouble("total"));
                order.setUserId(rs.getInt("userId"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<Order> getListOrderByHotelId1(int hotelId) {
        return getAll("where `hotelId` = '"+hotelId+"' LIMIT 0, 1");
    }

    public boolean haveOrderByHotelId(int hotelId) {

        List<Order> list=  getAll("where `hotelId` = '"+hotelId+"' LIMIT 0, 1");
        if (list.size()==0){
            return true;
        }
        return false;

    }
    public List<Order> getListOrderByUserId(int userId) {
        return getAll("where `userId` = "+userId);
    }
}
