package by.htp.booking.service.validation;

import by.htp.booking.bean.ApartmentFacility;
import by.htp.booking.bean.Facility;
import by.htp.booking.dao.impl.ApartmentFacilityDao;
import by.htp.booking.dao.impl.FacilityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FacilityValidate {

    @Autowired
    private ApartmentFacilityDao apartmentFacilityDao;

    @Autowired
    private FacilityDao facilityDao;

    public boolean checkDeleteData(Facility facility){

        List<ApartmentFacility> apartmentFacilityList  =
                apartmentFacilityDao.getListByFacilityId(facility.getId());

        if (apartmentFacilityList.size()==0){
            return true;
        }
        return false;
    }

    public boolean checkCreateData(Facility facility){

        if (facility.getName().isEmpty()){
            return false;
        }

        if (facilityDao.isDuplicated(facility)){
            return true;
        }

        return false;
    }
}
