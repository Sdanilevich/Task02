package by.htp.booking.service.impl;

import by.htp.booking.bean.ApartmentFacility;
import by.htp.booking.bean.Facility;
import by.htp.booking.dao.impl.ApartmentFacilityDao;
import by.htp.booking.dao.impl.FacilityDao;
import by.htp.booking.service.validation.FacilityValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class FacilityService {
    @Autowired
    private FacilityDao facilityDao;
    @Autowired
    private ApartmentFacilityDao apartmentFacilityDao;
    @Autowired
    private FacilityValidate facilityValidate;



    public boolean create(Facility facility) throws SQLException {
        if (facilityValidate.checkCreateData(facility)){
           return  facilityDao.create(facility);
        }
        return false;
    }

    public boolean checkDeleteData(Facility facility){

        List<ApartmentFacility> apartmentFacilityList  =
                apartmentFacilityDao.getListByFacilityId(facility.getId());

        if (apartmentFacilityList.size()==0){
            return true;
        }
        return false;
    }

    public List<Facility> getAll(String where) {
        return facilityDao.getAll("");
    }

    public Facility getFacilityById(int facilityId) {
        List<Facility> list = getAll("where `id`="+facilityId+" LIMIT 0,1");
        if (list.size()!=0){
            return list.get(0);
        }
        return null;
    }
}
