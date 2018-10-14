package by.htp.booking.controller.command;

import by.htp.booking.bean.Apartment;
import by.htp.booking.bean.Order;
import by.htp.booking.bean.User;
import by.htp.booking.controller.ActionFactory;
import by.htp.booking.controller.Util;

import by.htp.booking.service.impl.ApartmentService;
import by.htp.booking.service.impl.OrderService;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class CmdCreateOrder extends Cmd {

    OrderService orderService = ActionFactory.orderService;

    ApartmentService apartmentService = ActionFactory.apartmentService;

    private static final Logger log = Logger.getLogger(CmdCreateOrder.class);

    @Override
    public Cmd execute(HttpServletRequest req) throws Exception {
        User user = ActionFactory.getUserFromSession(req);
        if (user==null){
            log.info("Order can not create without user. Redirect to form logination ");
            return ActionsClient.LOGIN.command;
        }
        else {

            int apartment_id = Integer.valueOf(req.getParameter("apartmentId"));
            Date beginDate = Util.getDateFromFormat(req.getParameter("dateBegin"));
            Date dateEnd = Util.getDateFromFormat(req.getParameter("dateEnd"));
            Apartment apartment = apartmentService.getApartmentById(apartment_id);
            int countDay = Util.getCountDay(beginDate, dateEnd);
            if(apartment!=null){
                Order order = new Order();
                order.setUserId(user.getId());
                order.setApartmentId(apartment_id);

                order.setBeginDate(beginDate.getTime());
                order.setEndDate(dateEnd.getTime());
                order.setCountDay(countDay);
                order.setPrice(apartment.getPrice());
                order.setTotal(apartment.getPrice()*countDay);
                log.info("Try to create order by user "+user.getName());
                if (orderService.create(order)){
                    log.info("Create order by user "+user.getName());
                     return ActionsClient.USERORDER.command;
                }
                else {
                    req.setAttribute("err", "Error creating order");
                    log.error("Can't сreate order");
                    return ActionsClient.ERROR.command;
                }
            }
            else{
                HttpSession session = req.getSession();
                session.setAttribute("err", "Indefined apartment's id");
                log.error("Indefined apartment's id");
                return ActionsClient.ERROR.command;
            }

        }

    }
}
