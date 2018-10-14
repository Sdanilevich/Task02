package by.htp.booking.dao.impl;

import by.htp.booking.bean.Facility;
import by.htp.booking.dao.BeanDao;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class FacilityDao extends BeanDao<Facility> {

    @Override
    public List<Facility> getAll(String where) {
        List<Facility> facilityList = new ArrayList<>();
        String sql = String.format("Select * from `facility`" + where);
        try (Connection connection = DaoConnect.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Facility facility = new Facility();
                facility.setId(rs.getInt("id"));
                facility.setName(rs.getString("name"));
                facility.setNameEn(rs.getString("nameEn"));
                facilityList.add(facility);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facilityList;
    }

    public Facility findByName(String name){
        String where = "where `name` = " + name;
        List<Facility> facilityList = getAll(where);
        if (facilityList.size()!=0){
            return facilityList.get(0);
        }
        return null;
    }

    public boolean isDuplicated(Facility facility) {
        List<Facility> facilityList = getAll("where `name`='"+facility.getName()+"' LIMIT 0, 1");
        if (facilityList.size()==0){
            return false;
        }
        return true;
    }

    public boolean isDuplicated(String name) {
        List<Facility> facilityList = getAll("where `name`='"+name+"' LIMIT 0, 1");
        if (facilityList.size()==0){
            return false;
        }
        return true;
    }

}
