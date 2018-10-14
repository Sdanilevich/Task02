package by.htp.booking.service.validation;

import by.htp.booking.bean.Hotel;
import by.htp.booking.dao.impl.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelValidate {

    @Autowired
    private OrderDao orderDao;

    public boolean checkDeleteData(Hotel hotel){


        if (orderDao.haveOrderByHotelId(hotel.getId())){
            return false;
        }
        return true;

    }

    public boolean checkCreateData(Hotel hotel) {
        if (hotel.getCityId()==0||
                hotel.getName().isEmpty()||
                hotel.getAddress().isEmpty()){
            return false;
        }

        return true;
    }
}
