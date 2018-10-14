package by.htp.booking.dao.impl;

import by.htp.booking.bean.ApartmentFacility;
import by.htp.booking.bean.Facility;
import by.htp.booking.controller.ActionFactory;
import by.htp.booking.dao.BeanDao;
import by.htp.booking.service.impl.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApartmentFacilityDao extends BeanDao<ApartmentFacility> {
    @Autowired
    FacilityService facilityService;

    @Override
    public List<ApartmentFacility> getAll(String where) {
        List<ApartmentFacility> apartmentFacilityList = new ArrayList<>();
        String sql = String.format("Select * from `apartmentfacility`" + where);
        try (Connection connection = DaoConnect.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                ApartmentFacility apartmentFacility = new ApartmentFacility();
                apartmentFacility.setApartmentId(rs.getInt("apartmentId"));
                apartmentFacility.setFacilityId(rs.getInt("facilityId"));
                apartmentFacilityList.add(apartmentFacility);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartmentFacilityList;

    }

    public List<ApartmentFacility> getListByFacilityId(int facilityId) {
        return getAll("where `facilityId` = "+facilityId);
    }

    public boolean isDuplicated(int apartmentId, int facilityId) {
        List<ApartmentFacility> apartmentFacilityList =
                getAll("where `apartmentId`='"+apartmentId+"' AND "+
                        " `facilityId`='"+facilityId+"'");
        if (apartmentFacilityList.size()==0){
            return true;
        }
        return false;
    }

    public boolean isDuplicated(ApartmentFacility apartmentFacility) {
        List<ApartmentFacility> apartmentFacilityList =
                getAll("where `apartmentId`='"+apartmentFacility.getApartmentId()+"' AND "+
                        " `facilityId`='"+apartmentFacility.getFacilityId()+"'");
        if (apartmentFacilityList.size()==0){
            return true;
        }
        return false;
    }

    public List<Facility> getListFacilityForApartment(int idApartment) {
        List<Facility> facilityList = new ArrayList<>();
        String sql = String.format("SELECT facility.* from `apartmentfacility`, `facility` WHERE apartmentfacility.apartmentId=" + idApartment +
                " GROUP BY facility.id");
        try (Connection connection = DaoConnect.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Facility facility = new Facility();
                facility.setName(rs.getString("name"));
                facility.setNameEn(rs.getString("nameEn"));
                facilityList.add(facility);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facilityList;

    }
}
