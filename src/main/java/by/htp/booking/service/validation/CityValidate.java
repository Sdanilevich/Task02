package by.htp.booking.service.validation;

import by.htp.booking.bean.City;
import by.htp.booking.bean.Hotel;
import by.htp.booking.dao.impl.CityDao;
import by.htp.booking.dao.impl.HotelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityValidate {
    @Autowired
    private CityDao cityDao;

    @Autowired
    private HotelDao hotelDao;

    public boolean checkDeleteData(City city){
        List<Hotel> hotelList  = hotelDao.getListHotelByCountryId(city.getCountryId());

        if (hotelList.size()==0){
            return true;
        }
        return false;
    }

    public boolean checkCreateData(City city){

        if (!checkUpdateData(city)){
            return false;
        }

        if (cityDao.isDuplicated(city.getName(), city.getCountryId())){
            return false;
        }

        return true;
    }

    public boolean checkUpdateData(City city){

        if (city.getName().isEmpty()){
            return false;
        }

        if (city.getCountryId()==0){
            return false;
        }

        return true;
    }

}
