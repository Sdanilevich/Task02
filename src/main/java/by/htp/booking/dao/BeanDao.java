package by.htp.booking.dao;

import by.htp.booking.bean.AbstractBean;
import by.htp.booking.bean.Order;
import by.htp.booking.dao.impl.DaoConnect;
import by.htp.booking.service.impl.OrderService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BeanDao<Type extends AbstractBean> {

    public abstract List<Type>  getAll(String where);

    public boolean create(Type bean ) throws SQLException{
        String sql =UtilSQL.getSqlCreate(bean);
        System.out.println(sql);
        int id = executeUpdate(sql,true);
        if (id>0){
            bean.setId(id);
            return true;
        }
        return false;

    }

    public Type read( int id){
        List<Type> beanList = getAll("WHERE ID=" + id + " LIMIT 0,1");
        if (beanList.size() > 0) {
            return beanList.get(0);
        }
        else{
            return null;
        }
    }

    public boolean update(Type bean) throws SQLException{
        String sql =UtilSQL.getSqlUpdate(bean);
        if (executeUpdate(sql,false)==1){
            return true;
        }
        return false;
    }

    public  boolean delete(Type bean) throws SQLException{
            String sql = UtilSQL.getSqlDelete(bean);
            return (executeUpdate(sql, false)==1);

    }


    public int executeUpdate(String sql, boolean generatedID) throws SQLException {
        try (Connection connection = DaoConnect.getConnection();
             Statement statement = connection.createStatement()
        ) {
            int result = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if ((result>0)&&generatedID) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return id;
                }
            }
            return result;
        }
    }

}
