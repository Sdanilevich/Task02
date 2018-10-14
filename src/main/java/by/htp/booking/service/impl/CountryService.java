package by.htp.booking.service.impl;

import by.htp.booking.bean.Country;
import by.htp.booking.dao.impl.CountryDao;
import by.htp.booking.service.validation.CountryValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class CountryService {

    @Autowired
    private CountryDao countryDao;
    @Autowired
    private CountryValidate countryValidate;


    public boolean create(Country country) throws SQLException {
        if(countryValidate.checkCreateData(country)){
            return countryDao.create(country);
        }
        return false;
    }

    public boolean update(Country country) throws SQLException {
        if (countryValidate.checkCreateData(country)) {
           return countryDao.update(country);
        }
        return false;
    }
    public boolean delete(Country country) throws SQLException {
        if (countryValidate.checkDeleteData(country)) {
            return countryDao.delete(country);
        }
        return false;
    }

    public Country read(int id){
        return countryDao.read(id);
    }

    public Country findByName(String name){
        return countryDao.findByName(name);
    }

    public List<Country> getAll(String where) {
        return countryDao.getAll("");
    }
}
