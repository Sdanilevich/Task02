package by.htp.booking.controller.command;

import by.htp.booking.bean.Order;
import by.htp.booking.bean.User;
import by.htp.booking.controller.ActionFactory;
import by.htp.booking.service.impl.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CmdUserOrder extends Cmd {

    OrderService  orderService = ActionFactory.orderService;
    private static final Logger log = Logger.getLogger(CmdUserOrder.class);
    @Override
    public Cmd execute(HttpServletRequest req) throws Exception {
        if (req.getMethod().equalsIgnoreCase("get")) {
            log.info("Go to user's page ");
            User user = ActionFactory.getUserFromSession(req);
            if (user!=null) {
                int userId = user.getId();
                if (userId != 0) {
                    List<Order> userOrder = orderService.getListOrderByUserId(userId);
                    req.setAttribute("userOrder", userOrder);
                    } else {
                    req.setAttribute("err", "No user defined");
                    log.error("user don't defined");
                    return ActionsClient.ERROR.command;
                }
            }
            else {
                log.info("user don't defined");
                return ActionsClient.LOGIN.command;
            }
        }
        else{

        }
        return null;
    }
}
