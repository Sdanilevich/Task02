package by.htp.booking.service.validation;

import by.htp.booking.bean.City;
import by.htp.booking.bean.Country;
import by.htp.booking.dao.impl.CityDao;
import by.htp.booking.dao.impl.CountryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryValidate {

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CityDao cityDao;

    public boolean checkDeleteData(Country country){
        List<City> cityList  = cityDao.getAll("where `country_id` = '"+country.getId()+"' LIMIT 0, 1");

        if (cityList.size()==0){
            return true;
        }
        return false;
    }

    public boolean checkCreateData(Country country){

        if (country.getName().isEmpty()){
            return false;
        }

        if (countryDao.isDuplicated(country)){
            return false;
        }

        return false;
    }


}
