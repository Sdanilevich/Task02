package by.htp.booking.dao;

import by.htp.booking.bean.*;
import by.htp.booking.dao.impl.DaoConnect;
import by.htp.booking.service.impl.OrderService;
import org.apache.log4j.Logger;

import java.util.Locale;

public class UtilSQL {

    public static String getSqlUpdate(Object object){
        String sql = "";
        if (object.getClass().equals(Apartment.class)){
            Apartment apartment = (Apartment) object;
            sql = String.format(Locale.US,"UPDATE `apartment` SET " +
                            "`name`='%s', " +
                            "`nameEn`='%s', " +
                            "`description`='%s'," +
                            "`descriptionEn`='%s'," +
                            " `roomSize`='%f'," +
                            "`countGuest`='%d'," +
                            " `price`='%f' WHERE" +
                            " `id`=%d",
                    apartment.getName(),
                    apartment.getNameEn(),
                    apartment.getDescription(),
                    apartment.getDescriptionEn(),
                    apartment.getRoomSize(),
                    apartment.getCountGuest(),
                    apartment.getPrice(), apartment.getId());
        }
        else if (object.getClass().equals(City.class)){
            City city = (City) object;
            sql = String.format(Locale.US,"UPDATE `city` SET " +
                            "`name`='%s'," +
                            "`nameEn`='%s'," +
                            " `countryId`='%d' WHERE" +
                            " `id`=%d",
                    city.getName(),
                    city.getNameEn(),
                    city.getCountryId(),city.getId());
        }
       else if (object.getClass().equals(Country.class)){
            Country country = (Country) object;
            sql = String.format(Locale.US,"UPDATE `country` SET  " +
                    "`name`='%s', " +
                    "`nameEn`='%s' WHERE" +
                    " `id`=%d",
                    country.getName(),
                    country.getNameEn(),
                    country.getId());
        }
        else if (object.getClass().equals(Hotel.class)){
            Hotel hotel = (Hotel) object;
            sql = String.format(Locale.US,"UPDATE `hotel` SET " +
                            "`name`='%s'," +
                            "`nameEn`='%s',"+
                            "`description`='%s'," +
                            "`descriptionEn`='%s'," +
                            "`cityId`='%d'," +
                            "`address`='%s', " +
                            "`addressEn`='%s'" +
                            "WHERE `id`=%d",
                    hotel.getName(), hotel.getNameEn(), hotel.getDescription(),hotel.getDescriptionEn(),
                    hotel.getCityId(),
                    hotel.getAddress(),hotel.getAddressEn(),hotel.getId());        }

        else if (object.getClass().equals(Order.class)){
            Order order = (Order) object;
            sql = String.format(Locale.US,"UPDATE `order` SET " +
                            "`userId`='%d'," +
                            "`apartmentId`='%d', " +
                            "`beginDate`='%d'," +
                            "`endDate`='%d', " +
                            "`countDay`='%d', " +
                            "`price`='%d', " +
                            "`total`='%d' " +
                            "WHERE `id`=%d",
                    order.getUserId(), order.getApartmentId(),order.getBeginDate(),order.getEndDate(),
                    order.getCountDay(),order.getPrice(),order.getTotal(), order.getId());
         }
        else if (object.getClass().equals(ApartmentFacility.class)){
            ApartmentFacility apartmentFacility = (ApartmentFacility) object;
            sql = String.format(Locale.US,"UPDATE `roomFacility` SET " +
                            "`apartmentId`='%d'," +
                            "`facilityId`='%d'" +
                            "WHERE `id`=%d",
                    apartmentFacility.getApartmentId(), apartmentFacility.getFacilityId(),apartmentFacility.getId());
        }
        else if (object.getClass().equals(User.class)){
            User user = (User) object;
            sql = String.format(Locale.US,"UPDATE `user` SET " +
                            "`name`='%s'," +
                            "`login`='%s'," +
                            "`password`='%s'," +
                            "`email`='%s'"+
                            "WHERE `id`=%d",
                    user.getName(), user.getLogin(),user.getPassword(),user.getEmail(),user.getId());
        }

        return sql;
    }
    public static String getSqlDelete(Object object){
        String sql = "";
        if (object.getClass().equals(Apartment.class)){
            Apartment apartment = (Apartment) object;
            sql = String.format(Locale.US,"DELETE FROM `apartment` WHERE `id`=%d", apartment.getId());
        }
        else if (object.getClass().equals(City.class)){
            City city = (City) object;
            sql = String.format(Locale.US,"DELETE FROM `city` WHERE `id`=%d", city.getId());
        }
        else if (object.getClass().equals(Country.class)){
            Country country = (Country) object;
            sql = String.format(Locale.US,"DELETE FROM `country` WHERE `id`=%d", country.getId());
        }
        else if (object.getClass().equals(Facility.class)){
            Facility facility = (Facility) object;
            sql = String.format(Locale.US,"DELETE FROM `facility` WHERE `id`=%d", facility.getId());

        }
        else if (object.getClass().equals(Hotel.class)){
            Hotel hotel = (Hotel) object;
            sql = String.format(Locale.US,"DELETE FROM `hotel` WHERE `id`=%d", hotel.getId());

        }

        else if (object.getClass().equals(Order.class)){
            Order order = (Order) object;
            sql = String.format(Locale.US,"DELETE FROM `order` WHERE `id`=%d", order.getId());
        }

        else if (object.getClass().equals(User.class)){
            User user = (User) object;
            sql = String.format(Locale.US,"DELETE FROM `user` WHERE `id`=%d", user.getId());

        }

        return sql;
    }
    public static String getSqlCreate(Object object){

        String sql = "";
        if (object.getClass().equals(Apartment.class)){
            Apartment apartment = (Apartment) object;
             sql = String.format(Locale.US,"INSERT INTO `apartment`" +
                            "(`hotelId`, " +
                             "`name`, " +
                             "`description`," +
                             "`nameEn`, " +
                             "`descriptionEn`, " +
                             " `roomSize`," +
                             " `countGuest`, " +
                             "`price`)" +
                             "VALUES('%s','%s','%s','%s', '%s','%.2f', %d, '%.2f')",
                    apartment.getHotelId(),
                    apartment.getName(),
                    apartment.getDescription(),
                    apartment.getNameEn(),
                    apartment.getDescriptionEn(),
                    apartment.getRoomSize(),
                    apartment.getCountGuest(),
                     apartment.getPrice());
        }
        else if (object.getClass().equals(City.class)){
            City city = (City) object;
            sql = String.format(Locale.US,"INSERT INTO `city` (" +
                            "`name`, `countryId`) VALUES('%s', '%d') ",
                    city.getName(), city.getCountryId());
        }
        else if (object.getClass().equals(Country.class)){
            Country country = (Country) object;
            sql = String.format(Locale.US,"INSERT INTO `country`( " +
                    "`name`) VALUES('%s')", country.getName());
        }
        else if (object.getClass().equals(Facility.class)){
            Facility facility = (Facility) object;
            sql = String.format(Locale.US,"INSERT INTO `facility`(`name`,`nameEn`) Value('%s','%s')",
                    facility.getName(),facility.getNameEn());
        }
        else if (object.getClass().equals(Hotel.class)){
            Hotel hotel = (Hotel) object;
            sql = String.format(Locale.US,"INSERT INTO `hotel` (" +
                            "`name`," +
                            "`nameEn`," +
                            "`description`," +
                            "`descriptionEn`," +
                            "`address`, " +
                            "`addressEn`, " +
                            " `cityId`)" +
                            " Value('%s', '%s','%s', '%s','%s', '%s', %d)",
                    hotel.getName(),
                    hotel.getNameEn(),
                    hotel.getDescription(),
                    hotel.getDescriptionEn(),
                    hotel.getAddress(),
                    hotel.getAddressEn(),
                    hotel.getCityId());
        }

        else if (object.getClass().equals(Order.class)){
            Order order = (Order) object;
            sql = String.format(Locale.US,"INSERT INTO `order` " +
                            "(`userId`," +
                            "`apartmentId`," +
                            "`beginDate`," +
                            " `endDate`, " +
                            "`countDay`," +
                            "`price`," +
                            "`total`)" +
                            " VALUE(%d,%d,%d,%d,%d,%.2f,%.2f) " ,
                    order.getUserId(), order.getApartmentId(),order.getBeginDate(),order.getEndDate(),
                    order.getCountDay(),order.getPrice(),order.getTotal());
        }
        else if (object.getClass().equals(User.class)){
            User user = (User) object;
            sql = String.format(Locale.US,"INSERT INTO `user` (`name`, `login`,`password`,`email`) VALUE('%s','%s','%s','%s')",
                    user.getName(), user.getLogin(),user.getPassword(), user.getEmail());
        }

        return sql;
    }

}
