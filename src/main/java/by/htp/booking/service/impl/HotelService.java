package by.htp.booking.service.impl;

import by.htp.booking.bean.Hotel;
import by.htp.booking.dao.impl.HotelDao;
import by.htp.booking.service.validation.HotelValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class HotelService {
    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private HotelValidate hotelValidate;

    public boolean create(Hotel hotel) throws SQLException {
        if (hotelValidate.checkCreateData(hotel)){
            return hotelDao.create(hotel);
        }
        return false;
    }

    public List<Hotel> getAll(String where) {
        return hotelDao.getAll("");
    }

    public boolean update(Hotel hotel) throws SQLException {
        if (hotelValidate.checkCreateData(hotel)){
            return  hotelDao.update(hotel);
        }
        return false;
    }

    public Hotel getHotelById(int hotelId) {
        Hotel hotel = hotelDao.read(hotelId);
        return hotel;
    }
}
