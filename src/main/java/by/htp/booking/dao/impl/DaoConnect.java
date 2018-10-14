package by.htp.booking.dao.impl;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DaoConnect {

    private static  String URL_DB;
    private static String USER_DB;
    private static String PASSWORD_DB;

    static private Connection connection;

        static {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Driver driver = new FabricMySQLDriver();
                DriverManager.registerDriver(driver);

                ResourceBundle resource = ResourceBundle.getBundle("hotelData");
                URL_DB = resource.getString("connect.URL_DB");
                USER_DB= resource.getString("connect.USER_DB");;
                PASSWORD_DB= resource.getString("connect.PASSWORD_DB");;

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private DaoConnect() {
        }

        public static Connection getConnection() throws SQLException {
            if (connection == null || connection.isClosed()) {
                synchronized (DaoConnect.class) {
                    if (connection == null || connection.isClosed()) {
                        connection = DriverManager.getConnection(URL_DB, USER_DB, PASSWORD_DB);
                    }
                }
            }
            return connection;
        }


        public static void initBase(){

        }
    }

