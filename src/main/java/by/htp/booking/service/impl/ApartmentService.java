package by.htp.booking.service.impl;

import by.htp.booking.bean.Apartment;
import by.htp.booking.dao.impl.ApartmentDao;
import by.htp.booking.service.validation.ApartmentValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ApartmentService {

    @Autowired
    private ApartmentValidate apartmentValidate;
    @Autowired
    private ApartmentDao apartmentDao;

    public boolean create(Apartment apartment) throws SQLException {
        if (apartmentValidate.checkCreateData(apartment)){
            return apartmentDao.create(apartment);
        }
        return false;
    }

    public boolean update(Apartment apartment) throws SQLException {
        if (apartmentValidate.checkCreateData(apartment)){
            return apartmentDao.update(apartment);
        }
        return false;
    }

    public boolean delete(Apartment apartment) throws SQLException {
        if (apartmentValidate.checkDeleteData(apartment)){
            return apartmentDao.delete(apartment);
        }
        return false;
    }

    public List<Apartment> getAll(String where) {
        return apartmentDao.getAll(where);
    }


    public int getCountFreeApartments(long date, int countryId, int cityId) throws SQLException {
        if(cityId==0&&countryId==0){
            return apartmentDao.getCountByDate(date);
        }
        else if (cityId!=0){
            return apartmentDao.getCountDateAndCity(date, cityId);
        }
        else {
            return apartmentDao.getCountByDateAndCountry(date, countryId);
        }
    }


    public List<Apartment> getPortion(long date,  int countryId, int cityId, int from, int to) {
        if(cityId==0&&countryId==0){
            return apartmentDao.getPortionByDate(date, from, to);
        }
        else if (cityId!=0){
            return apartmentDao.getPortionByDateAndCity(date, cityId, from, to );
         }
        else {
            return apartmentDao.getPortionByDateAndCountry(date,countryId, from, to);
        }
    }

    public Apartment getApartmentById(int id) {
        return apartmentDao.read(id);
    }


    public List<String> getListPathImg(int apartmentId){
        List<String> listPath = new ArrayList<>();
        String pathFrom = "src"+
                File.separator+"main"+
                File.separator+
                "webapp"+File.separator+
                "views"+
                File.separator +"jpg"+File.separator+"apartments"+File.separator+apartmentId;
        File folderFrom = new File(pathFrom);
        if (folderFrom!=null) {
            File[] listOfFiles = folderFrom.listFiles();
                if (listOfFiles!=null){
                for (int i = 0; i < listOfFiles.length; i++) {
                    File file = listOfFiles[i];
                    listPath.add(pathFrom + File.separator + file.getName());
                }
            }
        }
        return  listPath;

    }

}
