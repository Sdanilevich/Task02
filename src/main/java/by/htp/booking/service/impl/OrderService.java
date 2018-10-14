package by.htp.booking.service.impl;

import by.htp.booking.bean.Order;
import by.htp.booking.dao.impl.OrderDao;
import by.htp.booking.service.validation.OrderValidate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderValidate orderValidate;


    private static final Logger log = Logger.getLogger(OrderService .class);
    public List<Order>  getListOrderByUserId(int userId){
        log.info("get list order by user id");
        return orderDao.getListOrderByUserId(userId);
    }

    public boolean create(Order order) throws SQLException {


        if (orderValidate.checkCorrectData(order)){
            log.info("correct date order is true");
            return orderDao.create(order);
        }

        return false;
    }

    public boolean deleteOrder(Order order) throws SQLException {
        log.info("delete order ");
        return orderDao.delete(order);
    }

    public Order getOrderById(int id) {
        if (id!=0){
           return orderDao.read(id);
        }
        return null;
    }
}
