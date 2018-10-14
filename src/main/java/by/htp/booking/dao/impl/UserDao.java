package by.htp.booking.dao.impl;

import by.htp.booking.bean.User;
import by.htp.booking.dao.BeanDao;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao extends BeanDao<User> {


    @Override
    public List<User> getAll(String where) {
        List<User> userList = new ArrayList<>();
        String sql = String.format("Select * from `user`" + where);
        try (Connection connection = DaoConnect.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User getUserByLogin(String login) {
        List<User> userList = getAll("where `login` = '"+login+"'");
        if (userList.size()!=0){
            return userList.get(0);
        }
        return null;
    }

    public User getUserByLoginPassword(String login, String password) {
        List<User> userList = getAll("where `login` = '"+login+"' AND `password`= '"+
                password+"'");
        if (userList.size()!=0){
            return userList.get(0);
        }
        return null;
    }
}
