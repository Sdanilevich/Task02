package by.htp.booking.service.impl;

import by.htp.booking.bean.ApartmentFacility;
import by.htp.booking.bean.Facility;
import by.htp.booking.dao.impl.ApartmentFacilityDao;
import by.htp.booking.service.validation.ApartmentFacilityValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class ApartmentFacilityService {
    @Autowired
    private ApartmentFacilityDao apartmentFacilityDao;
    @Autowired
    private ApartmentFacilityValidate apartmentFacilityValidate;

    public boolean create(ApartmentFacility apartmentFacility) throws SQLException {
        if (apartmentFacilityValidate.checkCreateData(apartmentFacility)){
            return apartmentFacilityDao.create(apartmentFacility);
        }
        return false;
    }

    public boolean delete(ApartmentFacility apartmentFacility) throws  SQLException {
             return apartmentFacilityDao.delete(apartmentFacility);
    }

    public List<Facility> getListFacilityForApartment(int idApartment) {
        return apartmentFacilityDao.getListFacilityForApartment(idApartment);
    }
}
