package by.htp.booking.controller.command;

import by.htp.booking.bean.Order;
import by.htp.booking.bean.User;
import by.htp.booking.controller.ActionFactory;
import by.htp.booking.service.impl.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CmdEditOrder extends Cmd {
    OrderService orderService = ActionFactory.orderService;
    private static final Logger log = Logger.getLogger(CmdEditOrder .class);
    @Override
    public Cmd execute(HttpServletRequest req) throws Exception {
        User user = ActionFactory.getUserFromSession(req);
        if (user!=null){
            int deleteId = Integer.valueOf(req.getParameter("deleteId"));
            if (deleteId!=0){
                Order order = orderService.getOrderById(deleteId);
                if (order!=null){
                    orderService.deleteOrder(order);
                    return ActionsClient.USERORDER.command;
                }
                else {
                    log.error("Order doesn't found");
                    return ActionsClient.ERROR.command;
                }
            }
        }
        return null;
    }
}
