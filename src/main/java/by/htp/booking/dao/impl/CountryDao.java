package by.htp.booking.dao.impl;

import by.htp.booking.bean.Country;
import by.htp.booking.dao.BeanDao;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountryDao extends BeanDao<Country> {


    @Override
    public List<Country> getAll(String where) {
        List<Country> countryList = new ArrayList<>();
        String sql = String.format("Select * from `country`" + where);
        try (Connection connection = DaoConnect.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Country country = new Country();
                country.setId(rs.getInt("id"));
                country.setName(rs.getString("name"));
                country.setNameEn(rs.getString("nameEn"));
                countryList.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryList;

    }


    public Country findByName(String name){
        String where = "where `name` = '" + name+"'";
        List<Country> countryList = getAll(where);
        if (countryList.size()!=0){
            return countryList.get(0);
        }
        return null;
    }

    public boolean isDuplicated(Country country) {
        List<Country> countryList = getAll("where `name`='"+country.getName()+"'");
        if (countryList.size()==0){
            return true;
        }
        return false;
    }

    public boolean isDuplicated(String name) {
        List<Country> countryList = getAll("where `name`='"+name+"'");
        if (countryList.size()==0){
            return true;
        }
        return false;
    }
}
