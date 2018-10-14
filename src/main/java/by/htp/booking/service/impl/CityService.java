package by.htp.booking.service.impl;

import by.htp.booking.bean.City;
import by.htp.booking.dao.impl.CityDao;
import by.htp.booking.service.validation.CityValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class CityService {

    @Autowired
    private CityDao cityDao;
    @Autowired
    private CityValidate cityValidate;

    public boolean create(City city) throws SQLException {
        if (cityValidate.checkCreateData(city)){
            return cityDao.create(city);
        }
        return false;
    }

    public boolean delete(City city) throws  SQLException {
        if (cityValidate.checkDeleteData(city)){
            return cityDao.delete(city);
        }

        return false;
    }

    public boolean update(City city) throws SQLException {
        if (cityValidate.checkUpdateData(city)){
            return cityDao.update(city);
        }
        return false;
    }

    public List<City> getlistCityByCountryId(int countryId){
        List<City> cityList = cityDao.getlistCityByCountryId(countryId);
        return cityList;
    }

    public List<City> getAll(String where) {
        return  cityDao.getAll("");
    }


    public City getCityByName(String name) {
        return cityDao.getByName(name);
    }
}
