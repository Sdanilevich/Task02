package by.htp.booking.dao.impl;

import by.htp.booking.bean.Apartment;
import by.htp.booking.bean.Order;
import by.htp.booking.dao.BeanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class  ApartmentDao extends BeanDao<Apartment> {

    @Autowired
    OrderDao orderDao;

    @Override
    public List getAll(String where) {
        List<Apartment> apartmentList = new ArrayList<>();
        String sql = String.format("Select * from `apartment`" + where);
        return getAllwithFullRequest(sql);
    }

    public List getAllwithFullRequest(String sql) {
        List<Apartment> apartmentList = new ArrayList<>();
          try (Connection connection = DaoConnect.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Apartment apartment = new Apartment();
                apartment.setId(rs.getInt("id"));
                apartment.setName(rs.getString("name"));
                apartment.setNameEn(rs.getString("nameEn"));
                apartment.setCountGuest(rs.getInt("countGuest"));
                apartment.setDescription(rs.getString("description"));
                apartment.setDescriptionEn(rs.getString("descriptionEn"));
                apartment.setHotelId(rs.getInt("hotelId"));
                apartment.setPrice(rs.getDouble("price"));
                apartment.setRoomSize(rs.getDouble("roomSize"));
                apartmentList.add(apartment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartmentList;

    }

    public boolean isApartmentInOrder(Apartment apartment) {
        List<Order> listOrder = orderDao.getAll("where `apartmentId` = "+apartment.getId()+" LIMIT 0,1");

        if (listOrder.size()!=0){
            return true;
        }
        else return false;
    }

    public Apartment getApartmentById(int id) {
        List<Apartment> listApartments = getAll("where `id` = "+id+" LIMIT 0,1");

        if (listApartments.size()!=0){
            return listApartments.get(0);
        }
        else return null;
    }

    public int getCountWithFullRequest(String sql){
        int rez = 0;

        try (Connection connection = DaoConnect.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()) {
                rez = rs.getInt("count_row");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rez;

    }



    public List<Apartment> getPortionByDateAndCity(Long beginEnd, int cityId, int from, int to){
        String sql = "SELECT apartment.* FROM apartment WHERE apartment.id NOT IN " +
                "(SELECT apartmentId FROM apartment LEFT JOIN `order` ON apartmentId = `order`.apartmentId " +
                "WHERE `apartment`.`hotelId` IN(SELECT id FROM `hotel` WHERE `hotel`.`cityId`=" +cityId+")" +
                " AND `order`.`endDate`>"+ beginEnd +")" +
                " AND apartment.hotelId IN(SELECT id FROM `hotel` WHERE `hotel`.`cityId`=" + cityId+") LIMIT "+from+", "+to;
        return getAllwithFullRequest(sql);

    }

    public List<Apartment> getPortionByDate(Long beginEnd, int from, int to){
        String sql = "SELECT apartment.* FROM apartment " +
                "WHERE apartment.id NOT IN " +
                "(SELECT apartment.id FROM apartment LEFT JOIN `order` ON apartment.id = `order`.apartmentId" +
                " WHERE `order`.`endDate`>" + beginEnd+") LIMIT "+from+", "+to;

        return getAllwithFullRequest(sql);
    }

    public List<Apartment> getPortionByDateAndCountry(Long beginEnd, int countryId, int from, int to){
        String sql = "SELECT apartment.*, hotel.id,apartment.id, city.id,country.id " +
                "FROM hotel, apartment, city, country " +
                "WHERE apartment.id NOT IN " +
                "(SELECT apartment.id FROM apartment LEFT JOIN `order` ON apartment.id = `order`.apartmentId WHERE `apartment`.`hotelId` IN" +
                "(SELECT id FROM `hotel` WHERE `hotel`.`cityId` IN(SELECT id FROM `country` WHERE id = "+countryId+"))" +
                " AND `order`.`endDate`>"+beginEnd+" ) AND country.id ="+countryId+" AND country.id=city.countryId AND city.id = hotel.cityId AND apartment.hotelId = hotel.id LIMIT "+from+", "+to;
        return getAllwithFullRequest(sql);

    }

    public int getCountDateAndCity(Long beginEnd, int cityId){
        String sql = "SELECT COUNT(*)  as `count_row`  FROM apartment WHERE apartment.id NOT IN " +
                "(SELECT apartment.id FROM apartment LEFT JOIN `order` ON apartment.id = `order`.apartmentId " +
                "WHERE `apartment`.`hotelId` IN(SELECT id FROM `hotel` WHERE `hotel`.`cityId`=" +cityId+")" +
                " AND `order`.`endDate`>"+ beginEnd +")" +
                " AND apartment.hotelId IN(SELECT id FROM `hotel` WHERE `hotel`.`cityId`=" + cityId+")";
        return getCountWithFullRequest(sql);

    }

    public int getCountByDate(Long beginEnd){
        String sql = "SELECT COUNT(*)  as `count_row`  FROM apartment " +
                "WHERE apartment.id NOT IN " +
                "(SELECT apartment.id FROM apartment LEFT JOIN `order` ON apartment.id = `order`.apartmentId" +
                " WHERE `order`.`endDate`>" + beginEnd+")";

        return getCountWithFullRequest(sql);
    }

    public int getCountByDateAndCountry(Long beginEnd, int countryId){
        String sql = "SELECT COUNT(*)  as `count_row` " +
                "FROM hotel, apartment, city, country " +
                "WHERE apartment.id NOT IN " +
                "(SELECT apartment.id FROM apartment LEFT JOIN `order` ON apartment.id = `order`.apartmentId WHERE `apartment`.`hotelId` IN" +
                "(SELECT id FROM `hotel` WHERE `hotel`.`cityId` IN(SELECT id FROM `country` WHERE id = "+countryId+"))" +
                " AND `order`.`endDate`>"+beginEnd+" ) AND country.id ="+countryId+" AND country.id=city.countryId AND city.id = hotel.cityId AND apartment.hotelId = hotel.id";
        return getCountWithFullRequest(sql);

    }

}
