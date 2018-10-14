package by.htp.booking.controller.command;

import by.htp.booking.bean.Order;
import by.htp.booking.bean.User;
import by.htp.booking.controller.ActionFactory;
import by.htp.booking.controller.Util;
import by.htp.booking.service.impl.OrderService;
import by.htp.booking.service.impl.UserService;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CmdLogin extends Cmd {

    UserService userService = ActionFactory.userService;

    OrderService orderService = ActionFactory.orderService;

    private static final Logger log = Logger.getLogger(CmdLogin.class);

    @Override
    public Cmd execute(HttpServletRequest req) throws Exception {
        if (req.getMethod().equalsIgnoreCase("post")){
            String login=req.getParameter("login");
            String password= Util.doEncryptPassword(req.getParameter("password"));
            User user= userService.getUserByLoginPassword(login, password);

            if (user!= null){

                List<Order> orderList = orderService.getListOrderByUserId(user.getId());
                HttpSession session = req.getSession();
                session.setAttribute("user",user);
                if (orderList.size()==0){
                    return ActionsClient.SEARCHAPARTMENT.command;
                }

                req.setAttribute("userOrder", orderList);

                return ActionsClient.USERORDER.command;
            }

            else {
                req.setAttribute("err","You entered an login/password combination that doesn't match");
                log.error("Failed login attempt");
                return ActionsClient.ERROR.command;
            }
        }
        log.info("Go to login page ");
        return null;

    }
}
