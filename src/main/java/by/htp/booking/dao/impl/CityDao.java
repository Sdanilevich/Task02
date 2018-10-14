package by.htp.booking.dao.impl;

import by.htp.booking.bean.City;
import by.htp.booking.dao.BeanDao;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class CityDao extends BeanDao<City> {

    @Override
    public List<City> getAll(String where) {
        List<City> cityList = new ArrayList<>();
        String sql = String.format("Select * from `city`" + where);

        try (Connection connection = DaoConnect.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                city.setNameEn(rs.getString("nameEn"));
                city.setCountryId(rs.getInt("countryId"));
                cityList.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    public List<City> getlistCityByCountryId(int countryId) {
        return getAll("where `countryId`="+countryId);
    }

    public boolean isDuplicated(String name, int countryId) {
        List<City> cityList = getAll("where `name`='"+name+
                "' AND `countryId`='"+countryId+"' LIMIT 0,1");

        if (cityList.size()==0){
            return true;
        }
        return false;
       }

    public City getByName(String name) {
        List<City> cityList = getAll("where `name` = '"+name + "'");
        if (cityList.size()!=0){
            return cityList.get(0);
        }
        else return null;
    }
}
