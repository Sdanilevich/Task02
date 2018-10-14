package by.htp.booking.dao.impl;

import by.htp.booking.bean.Hotel;
import by.htp.booking.dao.BeanDao;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class HotelDao extends BeanDao<Hotel> {

    @Override
    public List<Hotel> getAll(String where) {
        List<Hotel> hotelList = new ArrayList<>();
        String sql = String.format("Select * from `hotel`" + where);
        try (Connection connection = DaoConnect.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setNameEn(rs.getString("nameEn"));
                hotel.setDescription(rs.getString("description"));
                hotel.setDescriptionEn(rs.getString("descriptionEn"));
                hotel.setAddress(rs.getString("address"));
                hotel.setAddressEn(rs.getString("addressEn"));
                hotel.setCityId(rs.getInt("cityId"));
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public List<Hotel> getListHotelByCountryId(int countryId) {
        return getAll("where `countryId` = '"+countryId +"' LIMIT 0, 1");
    }
}
