package by.htp.booking.controller.command;

import by.htp.booking.bean.User;
import by.htp.booking.controller.ActionFactory;
import by.htp.booking.controller.Util;
import by.htp.booking.service.impl.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CmdSignup extends Cmd {

    UserService userService = ActionFactory.userService;

    private static final Logger log = Logger.getLogger(CmdSignup.class);
    @Override
    public Cmd execute(HttpServletRequest req) throws Exception {
        if (req.getMethod().equalsIgnoreCase("post")){
            String login= req.getParameter("login");
            String name= req.getParameter("name");
            String password= Util.doEncryptPassword(req.getParameter("password"));
            String email= req.getParameter("email");
            User user=new User(name,login,password,email);
            log.info("Attempt to create new user");
            if (userService.create(user)) {
                log.info("Create new user " + user.getName());
                return ActionsClient.LOGIN.command;
            }
            else {
                req.setAttribute("err","Error registration user");
                log.error("Error registration user");
                return ActionsClient.ERROR.command;
            }
        }
        else if(req.getMethod().equalsIgnoreCase("get")){
            log.info("Go to registration page");
        }
        return null;
    }
}
