package by.htp.booking.controller.command;

import by.htp.booking.bean.Order;
import by.htp.booking.controller.ActionFactory;
import by.htp.booking.controller.Util;
import by.htp.booking.service.impl.ApartmentService;
import by.htp.booking.service.impl.CityService;
import by.htp.booking.service.impl.CountryService;
import by.htp.booking.service.impl.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CmdListApartment extends Cmd {

    ApartmentService apartmentService = ActionFactory.apartmentService;
    OrderService orderService = ActionFactory.orderService;

    private static final Logger log = Logger.getLogger(CmdListApartment.class);
    @Override
    public Cmd execute(HttpServletRequest req) throws Exception {
        int begin =1;
        if (req.getParameter("beginPortion")!=null){
            begin = Integer.valueOf(req.getParameter("beginPortion"));
        }

        req.setAttribute("listApartment", apartmentService.getPortion(0,0,0,begin-1, 5));
        req.setAttribute("countApartment", apartmentService.getCountFreeApartments(0,1,1));

        List<Order> orderList = orderService.getListOrderByUserId(19);
        for (Order order :orderList   ) {
            order.getApartmentName();
        }
        log.info("Get list apartment");
        return null;
    }
}
