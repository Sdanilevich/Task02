package by.htp.booking.service.impl;

import by.htp.booking.bean.User;
import by.htp.booking.dao.impl.UserDao;
import by.htp.booking.service.validation.UserValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserValidate userValidate;

    public boolean create(User user) throws SQLException {
        if (!userValidate.isDuplicated(user)){
            return userDao.create(user);
        }
        return false;
    }

    public List<User> getAll(String where) {
        return userDao.getAll("");
    }

    public User getUserByLoginPassword(String login, String password){
        return userDao.getUserByLoginPassword(login, password);
    }

}
