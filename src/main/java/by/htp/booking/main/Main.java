package by.htp.booking.main;

import by.htp.booking.bean.*;
import by.htp.booking.controller.ActionFactory;
import by.htp.booking.controller.Util;
import by.htp.booking.dao.UtilSQL;
import by.htp.booking.service.impl.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class Main {
    static OrderService orderService = ActionFactory.orderService;
    HotelService hotelService = ActionFactory.hotelService;

    public static void main(String[] args) throws SQLException, ParseException {

        ActionFactory actionFactory = new ActionFactory();



        List<Order> orderList = ActionFactory.orderService.getListOrderByUserId(19);
        for (Order order: orderList) {
            System.out.println(order.getApartmentName());
            System.out.println(order.getApartmentNameEn());
            System.out.println(order.getHotelName());
            System.out.println(order.getHotelNameEn());

        }
        System.out.println(orderList);
    }



    public   void createFolderForPict(){

        List<Hotel> list = hotelService.getAll("");
        String pathFrom = "src"+
                File.separator+"main"+
                File.separator+
                "webapp"+
                File.separator +"jpg"+File.separator+"From2";
        File folderFrom = new File(pathFrom);

        File[] listOfFiles = folderFrom.listFiles();
        int start = 0;
        for (Hotel hotel : list) {
            String PathApartmentJpg = "src" +
                    File.separator + "main" +
                    File.separator +
                    "webapp" +
                    File.separator + "jpg" + File.separator + "hotels";
 //           File folder = new File(PathApartmentJpg);
//            if (!folder.exists()) {
//                folder.mkdir();
//                System.out.println("creating directory " + folder.getAbsolutePath());
//            }
//            else {
//                continue;
//            }
            int countJpgInOneFolder = 0;
            for (int i = start; i<listOfFiles.length; i++) {
                File file = listOfFiles[i];
                if (countJpgInOneFolder==1){
                    break;
                }
                File fileNew = new File(PathApartmentJpg+File.separator+hotel.getId()+".jpg");
                file.renameTo(fileNew);
                countJpgInOneFolder = countJpgInOneFolder+1;

            }
            start = start+1;

        }
    }

}
