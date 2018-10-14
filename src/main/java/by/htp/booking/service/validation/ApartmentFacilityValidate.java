package by.htp.booking.service.validation;

import by.htp.booking.bean.ApartmentFacility;
import by.htp.booking.dao.impl.ApartmentFacilityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ApartmentFacilityValidate {
    @Autowired
    private ApartmentFacilityDao apartmentFacilityDao;

    public boolean checkCreateData(ApartmentFacility apartmentFacility){
        return apartmentFacilityDao.isDuplicated(apartmentFacility.getApartmentId(), apartmentFacility.getFacilityId());
    }
}
